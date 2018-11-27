package com.pfernand.pfuser.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final String TEMPLATE = "User not found with: %s";

    public UserNotFoundException(String identifier) {
        super(String.format(TEMPLATE, identifier));
    }
}
