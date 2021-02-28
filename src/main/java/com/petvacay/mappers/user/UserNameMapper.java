package com.petvacay.mappers.user;

import com.petvacay.dto.user.UserNameDTO;
import com.petvacay.entities.User;
import com.petvacay.mappers.GeneralMapper;
import org.springframework.stereotype.Component;

@Component
public class UserNameMapper implements GeneralMapper<User, UserNameDTO> {
    @Override
    public UserNameDTO convertToDto(User model) {
        return UserNameDTO.builder()
                .userId(model.getUserId())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .build();
    }

    @Override
    public User convertToModel(UserNameDTO dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .userId(dto.getUserId())
                .build();
    }
}
