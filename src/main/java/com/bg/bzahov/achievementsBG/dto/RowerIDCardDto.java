package com.bg.bzahov.achievementsBG.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RowerIDCardDto {
    private Long id;
    private Long rowerID;
    private String cardNumber;
}
