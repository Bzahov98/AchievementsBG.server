package com.bg.bzahov.achievementsBG.dto.auth.response;

import com.bg.bzahov.achievementsBG.dto.RowerIDCardDto;
import com.bg.bzahov.achievementsBG.model.Gender;
import com.bg.bzahov.achievementsBG.model.Rower;
import com.bg.bzahov.achievementsBG.model.RowerIDCard;
import com.bg.bzahov.achievementsBG.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

import static com.bg.bzahov.achievementsBG.controlers.utils.ControllersUtils.mapAndConvertToDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RowerResponseDto {
    private Long id;
    private String name;
    private Gender gender;
    private int age;
    private int yearOfBirth;
    private String username; // from UserEntity
    private List<RowerIDCardDto> rowerIDCards; // new field

    public static RowerResponseDto fromRower(Rower rower) {
        List<RowerIDCard> rowerIDCards = rower.getRowerIDCards();
        List<RowerIDCardDto> rowerIDCardDtos = Collections.emptyList();
        if (rowerIDCards != null) {
            rowerIDCardDtos = mapAndConvertToDto(rowerIDCards, RowerIDCardDto::fromRowerIDCard);
        }

        UserEntity user = rower.getUserOfRowerID();
        return RowerResponseDto.builder()
                .id(rower.getId())
                .name(rower.getName())
                .gender(rower.getGender())
                .age(rower.getAge())
                .yearOfBirth(rower.getYearOfBirth())
                .username((user != null)? user.getUsername() : "")
                .rowerIDCards(rowerIDCardDtos)
                .build();
    }
}
