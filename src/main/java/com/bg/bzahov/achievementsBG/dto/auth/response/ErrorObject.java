package com.bg.bzahov.achievementsBG.dto.auth.response;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private String request;
    private Date timestamp;
}