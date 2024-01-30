package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Deprecated
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    /*@PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody User registrationRequest) {
        // Validate user data
        if (!registrationRequest.isValid()) {
            return ResponseEntity.badRequest().body(new UserRegistrationResponse(false, "Invalid registration data"));
        }

        // Hash password
        String hashedPassword = BCrypt.hashpw(registrationRequest.getPassword(), BCrypt.gensalt());

        // Create user record in database
        User user = new User();
        user.setName(registrationRequest.getName());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(hashedPassword);

        user = userService.createUser(user);

        // Update login status
        userService.loginUser(user);

        // Return success response
        return ResponseEntity.ok(new UserRegistrationResponse(true, "User registered successfully"));
    }*/
}