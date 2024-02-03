package com.bg.bzahov.achievementsBG.dto;

import com.bg.bzahov.achievementsBG.model.RowerIDCard;
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

    public static RowerIDCardDto fromRowerIDCard(RowerIDCard rowerIDCard) {
        return RowerIDCardDto.builder()
                .id(rowerIDCard.getId())
                .cardNumber(rowerIDCard.getCardNumber())
                .build();
    }
}