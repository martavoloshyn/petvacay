package com.petvacay.security;

import com.petvacay.constants.ErrorMessage;
import com.petvacay.entities.User;
import com.petvacay.exceptions.JwtAuthenticationException;
import com.petvacay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public JWTUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(userEmail);
        if (user == null)
            throw new JwtAuthenticationException(ErrorMessage.NOT_AUTHENTICATED + userEmail);
        return new UserPrincipal(user);
    }
}
