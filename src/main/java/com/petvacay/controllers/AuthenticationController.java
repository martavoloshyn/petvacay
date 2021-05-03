package com.petvacay.controllers;

import com.petvacay.constants.ErrorMessage;
import com.petvacay.dto.authentication.AuthenticationRequestDto;
import com.petvacay.entities.User;
import com.petvacay.exceptions.IncorrectPasswordException;
import com.petvacay.exceptions.UserNotFoundByEmail;
import com.petvacay.security.CookieProvider;
import com.petvacay.security.Helper;
import com.petvacay.security.JWTTokenProvider;
import com.petvacay.services.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("petvacay/api/v1/sign-in")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;
    private UserServiceImpl userService;
    private CookieProvider cookieProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JWTTokenProvider jwtTokenProvider,
                                    UserServiceImpl userService,
                                    CookieProvider cookieProvider,
                                    PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.cookieProvider = cookieProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public void login(@RequestBody AuthenticationRequestDto requestDto, HttpServletResponse response) throws IOException {
        String userEmail = requestDto.getUserEmail();
        try {
            if (userService.existsUserByEmail(userEmail)) {

                if (userService.comparePasswordLogin(requestDto, passwordEncoder)) {
                    authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(userEmail, requestDto.getPassword()));
                }

                User user = userService.findUserByEmail(userEmail);

                List<Cookie> list = Helper.createList(
                        cookieProvider.createCookie("JWT", jwtTokenProvider.generateAccessToken(user)),
                        cookieProvider.createCookie("jwt", jwtTokenProvider.generateRefreshToken())
                );

                for (Cookie cookie : list) {
                    response.addCookie(cookie);
                }
            }
        } catch (UserNotFoundByEmail ex) {
            response.sendError(404, ErrorMessage.USER_NOT_FOUND_WITH_THIS_EMAIL + userEmail);
        } catch (IncorrectPasswordException ex) {
            response.sendError(401, ErrorMessage.INVALID_EMAIL_OR_PASSWORD);
        }
    }
}
