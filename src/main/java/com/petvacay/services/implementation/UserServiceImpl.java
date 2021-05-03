package com.petvacay.services.implementation;

import com.petvacay.constants.ConstantMessage;
import com.petvacay.constants.ErrorMessage;
import com.petvacay.constants.UserStatusConst;
import com.petvacay.dto.authentication.AuthenticationRequestDto;
import com.petvacay.dto.user.UserRegistrationDto;
import com.petvacay.entities.User;
import com.petvacay.entities.UserActivationRequest;
import com.petvacay.exceptions.IncorrectPasswordException;
import com.petvacay.exceptions.InvalidEmailException;
import com.petvacay.exceptions.InvalidUserRegistrationDataException;
import com.petvacay.exceptions.UserNotFoundByEmail;
import com.petvacay.mappers.user.UserRegistrationMapper;
import com.petvacay.repositories.UserActivationRequestRepository;
import com.petvacay.repositories.UserRepository;
import com.petvacay.services.MailSender;
import com.petvacay.services.UserService;
import com.petvacay.services.UserStatusService;
import com.petvacay.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private Validator validator;
    private UserRegistrationMapper userRegistrationMapper;
    private UserStatusService userStatusService;
    private UserActivationRequestRepository userActivationRequestRepository;
    private MailSender mailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           Validator validator,
                           UserRegistrationMapper userRegistrationMapper,
                           UserStatusService userStatusService,
                           UserActivationRequestRepository userActivationRequestRepository,
                           MailSender mailSender) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.userRegistrationMapper = userRegistrationMapper;
        this.userStatusService = userStatusService;
        this.userActivationRequestRepository = userActivationRequestRepository;
        this.mailSender = mailSender;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsUserByEmail(String userEmail) {
        if (!userRepository.existsUserByEmail(userEmail)) {
            throw new UserNotFoundByEmail(ErrorMessage.USER_NOT_FOUND_WITH_THIS_EMAIL + userEmail);
        } else
            return true;
    }

    @Override
    public boolean comparePasswordLogin(AuthenticationRequestDto requestDto, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(requestDto.getPassword(), findUserByEmail(requestDto.getUserEmail()).getPassword())) {
            throw new IncorrectPasswordException(ErrorMessage.INVALID_EMAIL_OR_PASSWORD);
        }
        return true;
    }

    @Override
    public void validateUser(UserRegistrationDto userRegistrationDto) {
        Map<String, String> map = new HashMap<>();

        if (!validator.validateEmail(userRegistrationDto.getEmail())) {
            map.put("invalidEmail", ErrorMessage.INVALID_EMAIL);
        }
        if (userRepository.findByEmail(userRegistrationDto.getEmail()) != null) {
            map.put("duplicateEmail", ErrorMessage.DUPLICATE_EMAIL);
        }
        if (!validator.validatePassword(userRegistrationDto.getPassword())) {
            map.put("invalidPassword", ErrorMessage.INVALID_USER_PASSWORD);
        }
        if (!map.isEmpty()) {
            throw new InvalidUserRegistrationDataException(map);
        }
    }

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {
        User user = userRegistrationMapper.convertToModel(userRegistrationDto);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserStatus(userStatusService.findStatus(UserStatusConst.NOT_ACTIVATED));
        user = userRepository.save(user);

        UserActivationRequest userActivationRequest = new UserActivationRequest(user.getUserId());
        userActivationRequestRepository.save(userActivationRequest);

        String message = ConstantMessage.ACTIVATION_EMAIL_TEXT
                + userActivationRequest.getActivationCode();

        mailSender.send(user.getEmail(), ConstantMessage.ACTIVATION_EMAIL_SUBJECT, message);
    }

    @Override
    public void activateUserByCode(String activationCode) {
        UserActivationRequest userActivationRequest = userActivationRequestRepository.findByActivationCode(activationCode);
        User user = userRepository.getOne(userActivationRequest.getUserId());
        if (user.getUserStatus().getUserStatusName().equals(UserStatusConst.ACTIVATED)) {
            throw new InvalidEmailException(ErrorMessage.DUPLICATE_EMAIL);
        }
        user.setUserStatus(userStatusService.findStatus(UserStatusConst.ACTIVATED));

        userRepository.save(user);
    }
}
