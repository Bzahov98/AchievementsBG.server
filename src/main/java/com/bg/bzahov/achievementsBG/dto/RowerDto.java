package com.bg.bzahov.achievementsBG.dto;

import com.bg.bzahov.achievementsBG.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RowerDto {
    private int id;
    private String name;
    private Gender gender;
    private String age;
    private String yearOfBirth;
}
