package com.bg.bzahov.achievementsBG.utils;

import com.bg.bzahov.achievementsBG.dto.auth.response.ErrorObject;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionUtils {
    public static ErrorObject createErrorObject(HttpStatus status, String message) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(status.value());
        errorObject.setMessage(message);
        errorObject.setTimestamp(new Date());
        return errorObject;
    }
}
