package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.dto.auth.LoginDto;
import com.bg.bzahov.achievementsBG.dto.auth.RegisterDto;
import com.bg.bzahov.achievementsBG.dto.auth.response.AuthResponseDto;
import com.bg.bzahov.achievementsBG.services.AuthServiceImpl;
import com.bg.bzahov.achievementsBG.services.base.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bg.bzahov.achievementsBG.constants.PathConstants.*;
import static com.bg.bzahov.achievementsBG.constants.StringConstants.USER_REGISTERED_SUCCESSFUL;

@RestController
@RequestMapping(BASE_URL + PATH_AUTH)
public class AuthController {

    private final IAuthService authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping(value = PATH_AUTH_REQUEST_LOGIN,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(authService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping(PATH_AUTH_REQUEST_REGISTER)
    public String register(@RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
        return USER_REGISTERED_SUCCESSFUL + registerDto.getUsername();
    }

    @GetMapping(PATH_AUTH_REQUEST_ROLES)
    public ResponseEntity<?> getRoles() {
        return new ResponseEntity<>(authService.findAllRoles(), HttpStatus.OK);
    }
}