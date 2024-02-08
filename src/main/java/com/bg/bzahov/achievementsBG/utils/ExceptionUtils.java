package com.bg.bzahov.achievementsBG.utils;

import com.bg.bzahov.achievementsBG.dto.auth.response.ErrorObject;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.stream.Collectors;

public class ExceptionUtils {

    public static ErrorObject createErrorObject(HttpStatus status, String message) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(status.value());
        errorObject.setMessage(message);
        errorObject.setTimestamp(new Date());
        return errorObject;
    }

    public static String extractViolationsFromException(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
    }
}
