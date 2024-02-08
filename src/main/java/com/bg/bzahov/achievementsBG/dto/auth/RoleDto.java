package com.bg.bzahov.achievementsBG.dto.auth;

import com.bg.bzahov.achievementsBG.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private String id;
    private String name;

    public static RoleDto fromRole(Role role) {
        return RoleDto.builder()
                .id(String.valueOf(role.getId()))
                .name(role.getName())
                .build();
    }
}
