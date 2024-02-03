package com.bg.bzahov.achievementsBG.exceptions.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class BaseNotFoundException extends RuntimeException {
    protected static final String RESOURCE_NAME = "Result ";

    public BaseNotFoundException(String msg) {
        super(RESOURCE_NAME + " can't be found with data you provided: " + msg);
    }
    public BaseNotFoundException(String resourceName, String msg) {
        super(resourceName + " can't be found with data you provided: " + msg);
    }
}
