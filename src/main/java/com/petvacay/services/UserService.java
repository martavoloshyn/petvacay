package com.petvacay.services;

import com.petvacay.dto.authentication.AuthenticationRequestDto;
import com.petvacay.dto.customer.CustomerRegistrationDTO;
import com.petvacay.dto.performer.PerformerRegistrationDTO;
import com.petvacay.dto.user.UserNameDTO;
import com.petvacay.dto.user.UserRegistrationDto;
import com.petvacay.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
    User findUserByEmail(String email);

    boolean existsUserByEmail(String userEmail);

    boolean comparePasswordLogin(AuthenticationRequestDto requestDto, PasswordEncoder passwordEncoder);

    void validateUser(UserRegistrationDto dto);

    void registerUser(UserRegistrationDto dto);

    void activateUserByCode(String activationCode);

    void registerCustomer(CustomerRegistrationDTO dto);

    void registerPerformer(PerformerRegistrationDTO dto);

    UserNameDTO getUserById(Long userId);
}
