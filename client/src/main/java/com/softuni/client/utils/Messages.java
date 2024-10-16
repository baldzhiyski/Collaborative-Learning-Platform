package com.softuni.client.utils;

import java.util.regex.Pattern;

public enum Messages {
    ;
    // Controllers constants
    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult";
    public static final String DOT = ".";
    public static final String USER_NOT_FOUND = "No such user found in the database ! !";

    public static final String INVALID_UNI_NAME = "No such university in the db !";

    public static final String INVALID_ACTIVATION_CODE = "The following activation code : %s is not valid !";
    public static final String INVALID_ROLE_TYPE = "Invalid type of role !";
    public static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    // Regex patterns
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@(abv\\.bg|yahoo\\.com|gmail\\.com)$";
    public static final String YOUTUBE_URL_REGEX = "^(http(s)??\\:\\/\\/)?(www\\.)?((youtube\\.com\\/watch\\?v=)|(youtu.be\\/))([a-zA-Z0-9\\-_])+";

    // Phone number regex pattern
    public static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\+(?:[0-9]●?){6,14}[0-9]$");
    public static final Pattern PATTERN_LOWER = Pattern.compile("[a-z]");
    public static final Pattern PATTERN_UPPER = Pattern.compile("[A-Z]");
    public static final Pattern PATTERN_DIGIT = Pattern.compile("[0-9]");
    public static final Pattern PATTERN_SYMBOL = Pattern.compile("[!@#$%^&*()_+<>?]");

    public static final String ACTIVATION_CODE_SYMBOLS = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789";
    public static final int ACTIVATION_CODE_LENGTH = 20;

    public static final String INVALID_COURSE = "No such course for the current university !"
            ;
}

