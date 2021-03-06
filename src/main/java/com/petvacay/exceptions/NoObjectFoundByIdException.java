package com.petvacay.exceptions;

public class NoObjectFoundByIdException extends RuntimeException {
    public NoObjectFoundByIdException(String message) {
        super(message);
    }
}
