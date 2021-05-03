package com.petvacay.mappers.user;

import com.petvacay.dto.user.UserRegistrationDto;
import com.petvacay.entities.User;
import com.petvacay.mappers.GeneralMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper implements GeneralMapper<User, UserRegistrationDto> {

    @Override
    public UserRegistrationDto convertToDto(User model) {
        return UserRegistrationDto.builder()
                .email(model.getEmail())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .password(model.getPassword())
                .role(model.getRole())
                .build();
    }

    @Override
    public User convertToModel(UserRegistrationDto dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
    }
}
