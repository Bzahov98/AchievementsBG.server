package com.bg.bzahov.achievementsBG.services.base;

import com.bg.bzahov.achievementsBG.dto.auth.LoginDto;
import com.bg.bzahov.achievementsBG.dto.auth.RegisterDto;
import com.bg.bzahov.achievementsBG.dto.auth.RoleDto;
import com.bg.bzahov.achievementsBG.dto.auth.response.AuthResponseDto;
import com.bg.bzahov.achievementsBG.model.UserEntity;

import java.util.List;

public interface IAuthService {
    UserEntity register(RegisterDto registerDto);
    AuthResponseDto login(LoginDto loginDto);
    List<RoleDto> findAllRoles();
    void changePassword(String username, String oldPassword, String newPassword);
    void resetPassword(String username);
    void updateUserDetails(String username, UserEntity userDetails);
    void deleteUser(String username);
}