package com.bg.bzahov.achievementsBG.dto.auth;

import lombok.Data;

@Data
public class RegisterDto extends BasicAuthDto {
    private String role;
}
