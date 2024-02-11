package com.bg.bzahov.achievementsBG.services;

import com.bg.bzahov.achievementsBG.dto.auth.LoginDto;
import com.bg.bzahov.achievementsBG.dto.auth.RegisterDto;
import com.bg.bzahov.achievementsBG.dto.auth.RoleDto;
import com.bg.bzahov.achievementsBG.dto.auth.response.AuthResponseDto;
import com.bg.bzahov.achievementsBG.exceptions.AuthenticationFailedException;
import com.bg.bzahov.achievementsBG.model.Role;
import com.bg.bzahov.achievementsBG.model.UserEntity;
import com.bg.bzahov.achievementsBG.repositories.RoleRepository;
import com.bg.bzahov.achievementsBG.repositories.UserRepository;
import com.bg.bzahov.achievementsBG.security.jwt.JWTGenerator;
import com.bg.bzahov.achievementsBG.services.base.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.*;
import static com.bg.bzahov.achievementsBG.constants.SecurityConstants.DEFAULT_ROLE_USER;
import static com.bg.bzahov.achievementsBG.utils.ServicesUtils.mapAndConvertEntityToDto;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    @Override
    public UserEntity register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new AuthenticationFailedException(ERROR_USERNAME_IS_TAKEN);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Optional<Role> optionalRole = roleRepository.findByName(registerDto.getRole());
        if (optionalRole.isPresent()) {
            Role roles = optionalRole.get();
            user.setRoles(Collections.singletonList(roles));
        } else {
            // Handle the case when the role is not found and set default role
            Optional<Role> userDefaultRole = roleRepository.findByName(DEFAULT_ROLE_USER);
            user.setRoles(List.of(userDefaultRole.get()));
        }
        return userRepository.save(user);
    }

    @Override
    public AuthResponseDto login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new AuthResponseDto(token);
    }


    @Override
    public List<RoleDto> findAllRoles() {
        return mapAndConvertEntityToDto(roleRepository.findAll(), RoleDto::fromRole);
    }

    // TODO: 3/29/2021 implement change password
    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        UserEntity user = getUserByUsername(username);

        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new AuthenticationFailedException(ERROR_INCORRECT_OLD_PASSWORD);
        }
    }

    // TODO: 3/29/2021 implement reset password
    @Override
    public void resetPassword(String username) {
        UserEntity user = getUserByUsername(username);

        String tempPassword = UUID.randomUUID().toString();
        user.setPassword(passwordEncoder.encode(tempPassword));
        userRepository.save(user);
        // send tempPassword to user's email
    }

    // TODO: 3/29/2021 implement update user details
    @Override
    public void updateUserDetails(String username, UserEntity userDetails) {
        UserEntity user = getUserByUsername(username);

        user.setUsername(userDetails.getUsername());
        user.setRoles(userDetails.getRoles());
        // update other fields as necessary
        userRepository.save(user);
    }

    // TODO: 3/29/2021 implement delete user
    @Override
    public void deleteUser(String username) {
        UserEntity user = getUserByUsername(username);
        userRepository.delete(user);
    }

    private UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(ERROR_USER_NOT_FOUND));
    }
}