package com.petvacay.mappers.user;

import com.petvacay.dto.user.UserRegistrationDto;
import com.petvacay.entities.User;
import com.petvacay.mappers.GeneralMapper;
import com.petvacay.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper implements GeneralMapper<User, UserRegistrationDto> {

    private RoleService roleService;

    @Autowired
    public UserRegistrationMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public UserRegistrationDto convertToDto(User model) {
        return UserRegistrationDto.builder()
                .email(model.getEmail())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .password(model.getPassword())
                .roleId(model.getRole().getRoleId())
                .build();
    }

    @Override
    public User convertToModel(UserRegistrationDto dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(roleService.getById(dto.getRoleId()))
                .build();
    }
}
