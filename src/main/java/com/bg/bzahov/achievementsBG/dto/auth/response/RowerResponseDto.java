package com.bg.bzahov.achievementsBG.dto.auth.response;

import com.bg.bzahov.achievementsBG.dto.RowerIDCardDto;
import com.bg.bzahov.achievementsBG.model.Gender;
import com.bg.bzahov.achievementsBG.model.Rower;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RowerResponseDto {
    private Long id;
    private String name;
    private Gender gender;
    private String age;
    private String yearOfBirth;
    private String username; // from UserEntity
    private List<RowerIDCardDto> rowerIDCards; // new field

    public static RowerResponseDto fromRower(Rower rower) {
        List<RowerIDCardDto> rowerIDCardDtos = rower.getRowerIDCards().stream()
                .map(RowerIDCardDto::fromRowerIDCard)
                .collect(Collectors.toList());

        return RowerResponseDto.builder()
                .id(rower.getId())
                .name(rower.getName())
                .gender(rower.getGender())
                .age(rower.getAge())
                .yearOfBirth(rower.getYearOfBirth())
                .username(rower.getUserOfRowerID().getUsername())
                .rowerIDCards(rowerIDCardDtos)
                .build();
    }
}
