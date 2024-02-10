package com.bg.bzahov.achievementsBG.services;

import com.bg.bzahov.achievementsBG.dto.UserDto;
import com.bg.bzahov.achievementsBG.model.Role;
import com.bg.bzahov.achievementsBG.model.UserEntity;
import com.bg.bzahov.achievementsBG.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.ERROR_USERNAME_NOT_FOUND;
import static com.bg.bzahov.achievementsBG.utils.ControllersUtils.mapAndConvertEntityToDto;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public List<UserDto> getAllUsers(boolean isDetailedData) {
        List<UserEntity> users = userRepository.findAll();

        if (isDetailedData) {
            return mapAndConvertEntityToDto(users, UserDto::fromUserEntityExtended);
        } else {
            return mapAndConvertEntityToDto(users, UserDto::fromUserEntity);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(ERROR_USERNAME_NOT_FOUND));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}