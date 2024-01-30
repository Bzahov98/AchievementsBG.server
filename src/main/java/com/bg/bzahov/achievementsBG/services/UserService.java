package com.bg.bzahov.achievementsBG.services;

import com.bg.bzahov.achievementsBG.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

/*    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void loginUser(User user) {
        // Update login status in the database
    }*/
}