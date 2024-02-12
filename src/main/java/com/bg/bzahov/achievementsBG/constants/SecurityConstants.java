package com.bg.bzahov.achievementsBG.constants;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 7000000; // 20 minutes
    public static final String SECURITY_PATH_AUTH_ALL = "/api/v1/auth/**";
    public static final String SECURITY_PATH_ROLES = "/api/v1/auth/roles";
    public static final String SECURITY_PATH_ROWERS_ALL = "/api/v1/rowers/**";

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String AUTH_TOKEN_TYPE_BEARER = "Bearer ";
    public static final String DEFAULT_ROLE_USER = "USER";
    public static final String CONTENT_TYPE = "application/json";
    public static final String ENCODING_UTF_8 = "UTF-8";




}
