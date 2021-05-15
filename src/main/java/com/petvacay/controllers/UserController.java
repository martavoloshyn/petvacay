package com.petvacay.controllers;

import com.petvacay.dto.user.UserNameDTO;
import com.petvacay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("petvacay/api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserNameDTO> filterPerformers(@PathVariable long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }
}
