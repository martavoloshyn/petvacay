package com.petvacay.exceptions;

public class UserNotFoundByEmail extends RuntimeException {
    public UserNotFoundByEmail(String message) {
        super(message);
    }
}
