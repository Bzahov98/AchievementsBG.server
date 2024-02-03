package com.bg.bzahov.achievementsBG.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RowerIDCardNotFoundException extends RuntimeException {

    public RowerIDCardNotFoundException(String msg) {
        super("Rower ID Card can't be found with data you provided: " + msg);
    }

    public RowerIDCardNotFoundException(Long msg) {
        super(msg.toString());
    }
}

