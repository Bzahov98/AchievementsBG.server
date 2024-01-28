package com.bg.bzahov.achievementsBG.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RowerIDCardNotFoundException extends RuntimeException {

    public RowerIDCardNotFoundException(String msg) {
        super(msg);
    }

    public RowerIDCardNotFoundException(Long msg) {
        super(msg.toString());
    }
}

