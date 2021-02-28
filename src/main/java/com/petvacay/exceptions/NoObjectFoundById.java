package com.petvacay.exceptions;

public class NoObjectFoundById extends RuntimeException {
    public NoObjectFoundById(String message) {
        super(message);
    }
}
