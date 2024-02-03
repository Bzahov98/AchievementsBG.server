package com.bg.bzahov.achievementsBG.exceptions;

public class ValidationFailedException extends RuntimeException {

    public ValidationFailedException(String msg) {
        super(msg);
    }

    public ValidationFailedException(Long msg) {
        super(msg.toString());
    }
}