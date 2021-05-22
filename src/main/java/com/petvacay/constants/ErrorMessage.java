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
    public static final String INVALID_EMAIL = "Email not correct";
    public static final String DUPLICATE_EMAIL = "Duplicate email";
    public static final String INVALID_CHARACTER = "Sorry! Filename contains invalid path sequence ";
    public static final String COULD_NOT_STORE_FILE = " could not be stored file. Please try again!";
    public static final String FILE_NOT_FOUND = "File not found: ";
    public static final String COULD_NOT_SET_CREDENTIALS = "Couldn't set Google credentials from firebase configuration file";
    public static final String SUCH_EXTENSION_IS_NOT_ALLOWED = "Such extension not allowed";
    public static final String FILE_NOT_FOUND_BY_NAME_AND_USER_ID = "File not found in DB: ";
}
