package com.petvacay.controllers;

import com.petvacay.dto.user.UserRegistrationDto;
import com.petvacay.exceptions.InvalidUserRegistrationDataException;
import com.petvacay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("petvacay/api/v1/registration")
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserRegistrationDto> registration(@RequestBody UserRegistrationDto dto) {
        try {
            userService.validateUser(dto);
            userService.registerUser(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (InvalidUserRegistrationDataException e) {
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/activation")
    public ResponseEntity<Void> activationUser(@RequestParam(name = "activationCode") String activationCode) {
        userService.activateUserByCode(activationCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}