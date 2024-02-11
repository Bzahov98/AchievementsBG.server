package com.bg.bzahov.achievementsBG.exceptions.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class BaseNotFoundException extends RuntimeException {
    protected static final String RESOURCE_NAME = "Result ";
    public static final String CAN_T_BE_FOUND_WITH_DATA_YOU_PROVIDED = " can't be found with data you provided: ";

    public BaseNotFoundException(String msg) {
        super(RESOURCE_NAME + CAN_T_BE_FOUND_WITH_DATA_YOU_PROVIDED + msg);
    }
    public BaseNotFoundException(String resourceName, String msg) {
        super(resourceName + CAN_T_BE_FOUND_WITH_DATA_YOU_PROVIDED + msg);
    }
}
