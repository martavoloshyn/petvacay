package com.petvacay.constants;

public class ErrorMessage {
    public static final String ORDER_NOT_FOUND_BY_ID = "The order does not exist by this id: ";
    public static final String FEEDBACK_NOT_FOUND_FOR_ORDER = "The feedback does not exist for order with this id: ";
    public static final String ORDER_STATUS_NOT_FOUND_BY_NAME = "The order status does not exist by this name: ";
    public static final String DUPLICATE_FEEDBACK_FOR_ORDER = "The feedback already exists for this order: ";
    public static final String JWT_TOKEN_IS_EXPIRED = "Please try to login in your account again!";
    public static final String NOT_AUTHENTICATED = "User is not authenticated by this email: ";
    public static final String USER_NOT_FOUND_WITH_THIS_EMAIL = "The user does not exists by this email: ";
    public static final String INVALID_EMAIL_OR_PASSWORD = "Invalid email or password ";
    public static final String INVALID_USER_PASSWORD = "Weak password. Should contain 4-15 symbols";
    public static final String INVALID_EMAIL = "Email not correct";
    public static final String DUPLICATE_EMAIL = "Duplicate email";
}
