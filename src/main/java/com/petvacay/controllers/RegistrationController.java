package com.petvacay.controllers;

import com.petvacay.dto.customer.CustomerRegistrationDTO;
import com.petvacay.dto.performer.PerformerRegistrationDTO;
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

    @PostMapping("/customer")
    public ResponseEntity<CustomerRegistrationDTO> registerCustomer(@RequestBody CustomerRegistrationDTO dto) {
        userService.registerCustomer(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/performer")
    public ResponseEntity<PerformerRegistrationDTO> registerPerformer(@RequestBody PerformerRegistrationDTO dto) {
        userService.registerPerformer(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/activation")
    public ResponseEntity<Void> activationUser(@RequestParam(name = "activationCode") String activationCode) {
        userService.activateUserByCode(activationCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}