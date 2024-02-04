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
    private Long id;
    private String name;
    private Gender gender;
    private int age;
    private int yearOfBirth;
}
