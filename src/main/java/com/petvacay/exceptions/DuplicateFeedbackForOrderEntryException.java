package com.petvacay.exceptions;

public class DuplicateFeedbackForOrderEntryException extends RuntimeException {
    public DuplicateFeedbackForOrderEntryException(String message) {
        super(message);
    }
}
