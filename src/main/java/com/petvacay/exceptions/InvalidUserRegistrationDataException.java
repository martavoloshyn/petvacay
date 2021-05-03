package com.petvacay.exceptions;

import org.json.JSONObject;

import java.util.Map;

public class InvalidUserRegistrationDataException extends RuntimeException {

    public InvalidUserRegistrationDataException(String message) {
        super(message);
    }

    public InvalidUserRegistrationDataException(Map<String, String> messages) {
        super(new JSONObject(messages).toString());
    }
}
