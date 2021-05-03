package com.petvacay.dto.authentication;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String userEmail;
    private String password;
}
