package com.bg.bzahov.achievementsBG.services;

import com.bg.bzahov.achievementsBG.dto.auth.LoginDto;
import com.bg.bzahov.achievementsBG.dto.auth.RegisterDto;
import com.bg.bzahov.achievementsBG.dto.auth.response.AuthResponseDto;
import com.bg.bzahov.achievementsBG.exceptions.AuthenticationFailedException;
import com.bg.bzahov.achievementsBG.model.Role;
import com.bg.bzahov.achievementsBG.model.UserEntity;
import com.bg.bzahov.achievementsBG.repositories.RoleRepository;
import com.bg.bzahov.achievementsBG.repositories.UserRepository;
import com.bg.bzahov.achievementsBG.security.jwt.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class AuthService {

    public static final String USERNAME_IS_TAKEN = "Username is taken!";
    public static final String ROLE_USER = "USER";
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    public UserEntity register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new AuthenticationFailedException(USERNAME_IS_TAKEN);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Optional<Role> optionalRole = roleRepository.findByName(ROLE_USER);
        if (optionalRole.isPresent()) {
            Role roles = optionalRole.get();
            user.setRoles(Collections.singletonList(roles));
        } else {
            // Handle the case when the role is not found
        }
        return userRepository.save(user);
    }

    public AuthResponseDto login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new AuthResponseDto(token);
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
    // TODO: 3/29/2021 implement change password
    public void changePassword(String username, String oldPassword, String newPassword) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new AuthenticationFailedException("Old password is incorrect");
        }
    }

    // TODO: 3/29/2021 implement reset password
    public void resetPassword(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String tempPassword = UUID.randomUUID().toString();
        user.setPassword(passwordEncoder.encode(tempPassword));
        userRepository.save(user);
        // send tempPassword to user's email
    }
    // TODO: 3/29/2021 implement update user details
    public void updateUserDetails(String username, UserEntity userDetails) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setRoles(userDetails.getRoles());
        // update other fields as necessary
        userRepository.save(user);
    }

    // TODO: 3/29/2021 implement delete user
    public void deleteUser(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.delete(user);
    }
}