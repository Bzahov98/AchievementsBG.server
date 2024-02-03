package com.bg.bzahov.achievementsBG.exceptions;

import com.bg.bzahov.achievementsBG.exceptions.base.BaseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RowerIDCardNotFoundException extends BaseNotFoundException {

    public static final String RESOURCE_NAME = "Rower ID Card";

    public RowerIDCardNotFoundException(String msg) {
        super(RESOURCE_NAME, msg);
    }
}

