package com.bg.bzahov.achievementsBG.services;

import com.bg.bzahov.achievementsBG.dto.UserDto;
import com.bg.bzahov.achievementsBG.model.UserEntity;
import com.bg.bzahov.achievementsBG.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bg.bzahov.achievementsBG.utils.ControllersUtils.mapAndConvertEntityToDto;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto> getAllUsers(boolean isDetailedData) {
        List<UserEntity> users = userRepository.findAll();

        if (isDetailedData) {
            return mapAndConvertEntityToDto(users, UserDto::fromUserEntityExtended);
        } else {
            return mapAndConvertEntityToDto(users, UserDto::fromUserEntity);
        }
    }
}