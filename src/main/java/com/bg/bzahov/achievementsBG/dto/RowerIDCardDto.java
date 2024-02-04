package com.bg.bzahov.achievementsBG.dto;

import com.bg.bzahov.achievementsBG.model.Rower;
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
    private String cardNumber;

    private String rowerName;
    private String rowerGender;
    private String rowerAge;

    public static RowerIDCardDto fromRowerIDCard(RowerIDCard rowerIDCard) {
        Rower rower = rowerIDCard.getRower();
        if (rower != null) {
            return RowerIDCardDto.builder()
                    .id(rowerIDCard.getId())
                    .cardNumber(rowerIDCard.getCardNumber())
                    .rowerName(rower.getName())
                    .rowerGender(rower.getGender().toString())
                    .rowerAge(String.valueOf(rower.getAge()))
                    .build();
        } else {
            return RowerIDCardDto.builder()
                    .id(rowerIDCard.getId())
                    .cardNumber(rowerIDCard.getCardNumber())
                    .build();
        }
    }
}