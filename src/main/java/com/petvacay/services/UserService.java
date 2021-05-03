package com.petvacay.services;

import com.petvacay.dto.authentication.AuthenticationRequestDto;
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
}
