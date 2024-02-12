package com.bg.bzahov.achievementsBG.dto.auth.response;

import lombok.Data;

import static com.bg.bzahov.achievementsBG.constants.SecurityConstants.AUTH_TOKEN_TYPE_BEARER;

@Data
public class AuthResponseDto {

    private final String tokenType = AUTH_TOKEN_TYPE_BEARER;
    private String accessToken;
    private String fullAccessToken;

    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
        this.fullAccessToken = tokenType.concat(accessToken);
    }
}
