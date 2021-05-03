package com.petvacay.controllers;

import com.petvacay.security.CookieProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("petvacay/api/v1/sign-out")
public class LogoutController {

    private CookieProvider cookieProvider;

    @Autowired
    public LogoutController(CookieProvider cookieProvider) {
        this.cookieProvider = cookieProvider;
    }

    @PostMapping
    public void getHome(HttpServletRequest request, HttpServletResponse response) {
        List<Cookie> cookieList = new ArrayList<>();
        cookieList.add(cookieProvider.deleteCookie("JWT"));
        cookieList.add(cookieProvider.deleteCookie("jwt"));

        for (Cookie cookie : cookieList) {
            response.addCookie(cookie);
        }
    }
}
