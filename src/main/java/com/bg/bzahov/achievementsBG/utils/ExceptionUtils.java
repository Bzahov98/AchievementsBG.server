package com.bg.bzahov.achievementsBG.utils;

import com.bg.bzahov.achievementsBG.dto.auth.response.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.stream.Collectors;

public class ExceptionUtils {

    public static final String DELIMITER = ", ";

    public static ErrorObject createErrorObject(HttpStatus status, String message) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(status.value());
        errorObject.setMessage(message);
        errorObject.setTimestamp(new Date());
        return errorObject;
    }

    public static ErrorObject createErrorObject(HttpStatus status, String message, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(status.value());
        errorObject.setMessage(message);
        errorObject.setTimestamp(new Date());
        errorObject.setRequest(request.getDescription(false));
        return errorObject;
    }

    public static String extractViolationsFromException(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(DELIMITER));
    }
}
