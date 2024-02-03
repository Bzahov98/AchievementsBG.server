package com.bg.bzahov.achievementsBG.dto.auth.response;

import com.bg.bzahov.achievementsBG.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
