package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.dto.auth.LoginDto;
import com.bg.bzahov.achievementsBG.dto.auth.RegisterDto;
import com.bg.bzahov.achievementsBG.dto.auth.response.AuthResponseDto;
import com.bg.bzahov.achievementsBG.services.base.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bg.bzahov.achievementsBG.constants.PathConstants.*;

@RestController
@RequestMapping(BASE_URL + PATH_AUTH)
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = PATH_AUTH_REQUEST_LOGIN,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @PostMapping(PATH_AUTH_REQUEST_REGISTER)
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        return authService.register(registerDto);
    }

    @GetMapping(PATH_AUTH_REQUEST_ROLES)
    public ResponseEntity<?> getRoles() {
        return authService.findAllRoles();
    }
}