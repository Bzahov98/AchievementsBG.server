package com.bg.bzahov.achievementsBG.exceptions;

import com.bg.bzahov.achievementsBG.dto.auth.response.ErrorObject;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.ERROR_INVALID_VALUE_PROVIDED;
import static com.bg.bzahov.achievementsBG.utils.ExceptionUtils.createErrorObject;
import static com.bg.bzahov.achievementsBG.utils.ExceptionUtils.extractViolationsFromException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorObject> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        String errorMessage = extractViolationsFromException(ex);

        ErrorObject errorObject =
                createErrorObject(HttpStatus.BAD_REQUEST, errorMessage);

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorObject> handleUserNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        ErrorObject errorObject =
                createErrorObject(HttpStatus.NOT_FOUND, "User:" + ex.getMessage());
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RowerNotFoundException.class)
    public ResponseEntity<ErrorObject> handleRowerNotFoundException(RowerNotFoundException ex, WebRequest request) {
        ErrorObject errorObject =
                createErrorObject(HttpStatus.NOT_FOUND, ex.getMessage() + "| For request:" + request.getDescription(false));
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RowerIDCardNotFoundException.class)
    public ResponseEntity<ErrorObject> handleRowerIDCardNotFoundException(RowerIDCardNotFoundException ex, WebRequest request) {
        ErrorObject errorObject =
                createErrorObject(HttpStatus.NOT_FOUND, ex.getMessage() + " | For request:" + request.getDescription(false));
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<ErrorObject> handleValidationFailedException(ValidationFailedException ex, WebRequest request) {
        ErrorObject errorObject = createErrorObject(HttpStatus.BAD_REQUEST, ex.getMessage() + " | for request:" + request.getDescription(false));
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ErrorObject> handleCredentialsNotFoundException(AuthenticationCredentialsNotFoundException ex, WebRequest request) {
        ErrorObject errorObject = createErrorObject(HttpStatus.INTERNAL_SERVER_ERROR, "CredentialsNotFound: " + ex.getMessage() + " | for request:" + request.getDescription(false));
        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorObject> handleInvalidFormatException2(InvalidFormatException ex, WebRequest request) {
        ErrorObject errorObject = createErrorObject(HttpStatus.BAD_REQUEST, ERROR_INVALID_VALUE_PROVIDED);
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorObject> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        ErrorObject errorObject = createErrorObject(HttpStatus.BAD_REQUEST, ERROR_INVALID_VALUE_PROVIDED);
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorObject> handleIllegalStateExceptionException(IllegalStateException ex, WebRequest request) {
        ErrorObject errorObject =
                createErrorObject(HttpStatus.INTERNAL_SERVER_ERROR, "IllegalStateException: " + ex.getMessage() + " | for request:" + request.getDescription(false));
        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorObject> handleIOException(IOException ex, WebRequest request) {
        ErrorObject errorObject =
                createErrorObject(HttpStatus.INTERNAL_SERVER_ERROR, "IO: " + ex.getMessage() + " | for request:" + request.getDescription(false));
        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleException(Exception ex, WebRequest request) {
        ErrorObject errorObject =
                createErrorObject(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage() + " | for request:" + request.getDescription(false));
        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}