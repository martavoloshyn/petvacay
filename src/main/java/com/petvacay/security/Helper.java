package com.petvacay.security;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {
    public static List<Cookie> createList(Cookie... cookies) {
        return new ArrayList<>(Arrays.asList(cookies));
    }
}
