package com.bg.bzahov.achievementsBG.dto.auth.response;

import lombok.Data;

@Data
public class AuthResponseDTO {

    private final String tokenType = "Bearer ";
    private String accessToken;
    private String fullAccessToken;

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
        this.fullAccessToken = tokenType.concat(accessToken);
    }
}
