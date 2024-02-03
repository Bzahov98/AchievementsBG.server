package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.dto.auth.response.AuthResponseDto;
import com.bg.bzahov.achievementsBG.dto.auth.BasicAuthDto;
import com.bg.bzahov.achievementsBG.dto.auth.LoginDto;
import com.bg.bzahov.achievementsBG.dto.auth.RegisterDto;
import com.bg.bzahov.achievementsBG.model.Role;
import com.bg.bzahov.achievementsBG.model.UserEntity;
import com.bg.bzahov.achievementsBG.repositories.RoleRepository;
import com.bg.bzahov.achievementsBG.repositories.UserRepository;
import com.bg.bzahov.achievementsBG.security.SecurityConstants;
import com.bg.bzahov.achievementsBG.security.jwt.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/" + SecurityConstants.API_VERSION + "/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
//        if (!getExistsByUsername(loginDto)) {
//            throw new UsernameNotFoundException("Wrong Credentials!");
////            return new ResponseEntity<>(new AuthResponseDto("Wrong Credentials!",true), HttpStatus.FORBIDDEN);
//        }
        UsernamePasswordAuthenticationToken authentication1 = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                authentication1);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (getExistsByUsername(registerDto)) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @GetMapping("roles")
    public ResponseEntity<?> getRoles() {
        return new ResponseEntity<>(roleRepository.findAll(), HttpStatus.OK);
    }

    private Boolean getExistsByUsername(BasicAuthDto dto) {
        return userRepository.existsByUsername(dto.getUsername());
    }
}