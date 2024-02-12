package com.bg.bzahov.achievementsBG.dto;

import com.bg.bzahov.achievementsBG.model.Role;
import com.bg.bzahov.achievementsBG.model.Rower;
import com.bg.bzahov.achievementsBG.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.bg.bzahov.achievementsBG.utils.ServicesUtils.mapAndConvertEntitiesToDto;

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
        List<String> roles = null;

        Rower rower = userEntity.getRower();
        if (rower != null) {
            rowerDto = RowerDto.fromRower(rower);
        }

        if (userEntity.getRoles() != null) {
            roles = mapAndConvertEntitiesToDto(userEntity.getRoles(), Role::getName);
        }

        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .rower(rowerDto)
                .roles(roles)
                .build();
    }
}