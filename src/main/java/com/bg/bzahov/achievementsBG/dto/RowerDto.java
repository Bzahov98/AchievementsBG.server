package com.bg.bzahov.achievementsBG.dto;

import com.bg.bzahov.achievementsBG.exceptions.RowerNotFoundException;
import com.bg.bzahov.achievementsBG.model.Gender;
import com.bg.bzahov.achievementsBG.model.Rower;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RowerDto {
    private Long id;
    private String name;
    private Gender gender;
    private int age;
    private int yearOfBirth;

    public static RowerDto fromRower(Rower rower) {
        if (rower == null) {
            throw new RowerNotFoundException("");
        }
        return RowerDto.builder()
                .id(rower.getId())
                .name(rower.getName())
                .gender(rower.getGender())
                .age(rower.getAge())
                .yearOfBirth(rower.getYearOfBirth())
                .build();
    }
}
