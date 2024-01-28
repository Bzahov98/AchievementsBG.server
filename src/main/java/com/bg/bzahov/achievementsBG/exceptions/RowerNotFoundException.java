package com.bg.bzahov.achievementsBG.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RowerNotFoundException extends RuntimeException {

    public RowerNotFoundException(String msg) {
        super(msg);
    }

    public RowerNotFoundException(Long msg) {
        super(msg.toString());
    }
}
