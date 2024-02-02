package com.bg.bzahov.achievementsBG.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RowerDto {
    private int id;
    private String title;
    private String content;
    private int stars;
}
