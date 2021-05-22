package com.petvacay.services.implementation;

import com.petvacay.constants.ConstantMessage;
import com.petvacay.constants.ErrorMessage;
import com.petvacay.constants.UserStatusConst;
import com.petvacay.dto.authentication.AuthenticationRequestDto;
import com.petvacay.dto.customer.CustomerRegistrationDTO;
import com.petvacay.dto.performer.PerformerRegistrationDTO;
import com.petvacay.dto.user.UserNameDTO;
import com.petvacay.dto.user.UserRegistrationDto;
import com.petvacay.entities.*;
import com.petvacay.exceptions.IncorrectPasswordException;
import com.petvacay.exceptions.InvalidEmailException;
import com.petvacay.exceptions.InvalidUserRegistrationDataException;
import com.petvacay.exceptions.UserNotFoundByEmail;
import com.petvacay.mappers.category.CategoryMapper;
import com.petvacay.mappers.pet.NewPetMapper;
import com.petvacay.mappers.user.UserNameMapper;
import com.petvacay.mappers.user.UserRegistrationMapper;
import com.petvacay.repositories.*;
import com.petvacay.services.MailSender;
import com.petvacay.services.UserService;
import com.petvacay.services.UserStatusService;
import com.petvacay.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private Validator validator;
    private UserRegistrationMapper userRegistrationMapper;
    private UserStatusService userStatusService;
    private UserActivationRequestRepository userActivationRequestRepository;
    private MailSender mailSender;
    private CustomerRepository customerRepository;
    private PetRepository petRepository;
    private PerformerRepository performerRepository;
    private CategoryMapper categoryMapper;
    private NewPetMapper newPetMapper;
    private UserNameMapper userNameMapper;
    private GalleryRepository galleryRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           Validator validator,
                           UserRegistrationMapper userRegistrationMapper,
                           UserStatusService userStatusService,
                           UserActivationRequestRepository userActivationRequestRepository,
                           MailSender mailSender,
                           CustomerRepository customerRepository,
                           PetRepository petRepository,
                           PerformerRepository performerRepository,
                           CategoryMapper categoryMapper,
                           NewPetMapper newPetMapper,
                           UserNameMapper userNameMapper,
                           GalleryRepository galleryRepository) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.userRegistrationMapper = userRegistrationMapper;
        this.userStatusService = userStatusService;
        this.userActivationRequestRepository = userActivationRequestRepository;
        this.mailSender = mailSender;
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
        this.performerRepository = performerRepository;
        this.categoryMapper = categoryMapper;
        this.newPetMapper = newPetMapper;
        this.userNameMapper = userNameMapper;
        this.galleryRepository = galleryRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.getByEmail(email);
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
        user.setInfoFilled(false);
        user.setGallery(galleryRepository.save(new Gallery()));

        UserActivationRequest userActivationRequest = new UserActivationRequest(user.getUserId());

        if (user.getRole().getRoleName().equals("Customer")) {
            Customer customer = new Customer(user);
            customer.setUserId(user.getUserId());
            customerRepository.save(customer);
            userActivationRequest = new UserActivationRequest(customer.getUserId());
        } else if (user.getRole().getRoleName().equals("Performer")) {
            Performer performer = new Performer(user);
            performer.setUserId(user.getUserId());
            performerRepository.save(performer);
            userActivationRequest = new UserActivationRequest(performer.getUserId());
        }

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

    @Override
    public void registerCustomer(CustomerRegistrationDTO dto) {
        Customer customer = customerRepository.getOne(dto.getUserId());
        setCustomerProperties(dto, customer);
        customerRepository.save(customer);
        List<Pet> pets = new ArrayList<>();
        dto.getPets().forEach(pet -> pets.add(newPetMapper.convertToModel(pet)));
        pets.forEach(pet -> pet.setCustomer(customer));
        pets.forEach(pet -> petRepository.save(pet));
    }

    @Override
    public void registerPerformer(PerformerRegistrationDTO dto) {
        Performer performer = performerRepository.getOne(dto.getUserId());
        setPerformerProperties(dto, performer);
        performerRepository.save(performer);
    }

    @Override
    public UserNameDTO getUserById(Long userId) {
        return userNameMapper.convertToDto(userRepository.getUserByUserId(userId));
    }

    private void setPerformerProperties(PerformerRegistrationDTO dto, Performer performer) {
        performer.setAboutInfo(dto.getAboutInfo());
        performer.setApartment(dto.getApartment());
        performer.setBirthDate(dto.getBirthDate());
        performer.setBuilding(dto.getBuilding());
        performer.setCardNumber(dto.getCardNumber());
        performer.setCategories(categoryMapper.convertListToModel(dto.getCategories()));
        performer.setPhoneNumber(dto.getPhoneNumber());
        performer.setStreet(dto.getStreet());
        performer.setCity(dto.getCity());
        performer.setInfoFilled(true);
    }

    private void setCustomerProperties(CustomerRegistrationDTO dto, Customer customer) {
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setCity(dto.getCity());
        customer.setAboutInfo(dto.getAboutInfo());
        customer.setInfoFilled(true);
    }
}
