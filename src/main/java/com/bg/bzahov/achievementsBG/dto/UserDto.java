package com.bg.bzahov.achievementsBG.dto;

import com.bg.bzahov.achievementsBG.model.Role;
import com.bg.bzahov.achievementsBG.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private RowerDto rower;
    private List<String> roles;
    public static UserDto fromUserEntity(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .build();
    }

    public static UserDto fromUserEntityExtended(UserEntity userEntity) {
        RowerDto rowerDto = null;
        if (userEntity.getRower() != null) {
            rowerDto = RowerDto.builder()
                    .id(userEntity.getRower().getId())
                    .name(userEntity.getRower().getName())
                    .gender(userEntity.getRower().getGender())
                    .age(userEntity.getRower().getAge())
                    .yearOfBirth(userEntity.getRower().getYearOfBirth())
                    .build();
        }

        List<String> roles = userEntity.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .rower(rowerDto)
                .roles(roles)
                .build();
    }
}