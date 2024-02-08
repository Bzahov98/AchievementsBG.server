package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.dto.UserDto;
import com.bg.bzahov.achievementsBG.model.UserEntity;
import com.bg.bzahov.achievementsBG.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.bg.bzahov.achievementsBG.constants.PathConstants.BASE_URL;
import static com.bg.bzahov.achievementsBG.controlers.utils.ControllersUtils.mapAndConvertToDto;

@RestController
@RequestMapping(BASE_URL + "users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<UserDto> getAllUsers(
            @RequestParam(required = false) boolean isDetailedData
    ) {
        List<UserEntity> users = userRepository.findAll();

        if (isDetailedData) {
            return mapAndConvertToDto(users, UserDto::fromUserEntityExtended);
        } else {
            return mapAndConvertToDto(users, UserDto::fromUserEntity);
        }
    }
}