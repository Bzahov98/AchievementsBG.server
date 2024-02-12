package com.bg.bzahov.achievementsBG.services.base;

import com.bg.bzahov.achievementsBG.dto.auth.LoginDto;
import com.bg.bzahov.achievementsBG.dto.auth.RegisterDto;
import com.bg.bzahov.achievementsBG.dto.auth.RoleDto;
import com.bg.bzahov.achievementsBG.dto.auth.response.AuthResponseDto;
import com.bg.bzahov.achievementsBG.model.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {
    ResponseEntity<String> register(RegisterDto registerDto);
    ResponseEntity<AuthResponseDto> login(LoginDto loginDto);
    ResponseEntity<List<RoleDto>> findAllRoles();
    void changePassword(String username, String oldPassword, String newPassword);
    void resetPassword(String username);
    void updateUserDetails(String username, UserEntity userDetails);
    void deleteUser(String username);
}