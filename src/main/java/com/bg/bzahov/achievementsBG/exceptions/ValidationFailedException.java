package com.bg.bzahov.achievementsBG.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class ValidationFailedException extends RuntimeException {

    public ValidationFailedException(String msg) {
        super(msg);
    }

    public ValidationFailedException(Long msg) {
        super(msg.toString());
    }
}