package com.bg.bzahov.achievementsBG.exceptions.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.ERROR_NOT_FOUND_WITH_PROVIDED_DATA;

@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class BaseNotFoundException extends RuntimeException {
    protected static final String RESOURCE_NAME = "Result ";

    public BaseNotFoundException(String msg) {
        super(RESOURCE_NAME + ERROR_NOT_FOUND_WITH_PROVIDED_DATA + msg);
    }
    public BaseNotFoundException(String resourceName, String msg) {
        super(resourceName + ERROR_NOT_FOUND_WITH_PROVIDED_DATA + msg);
    }
}
