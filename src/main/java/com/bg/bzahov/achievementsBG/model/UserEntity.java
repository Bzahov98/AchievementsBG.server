package com.bg.bzahov.achievementsBG.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.*;
import static com.bg.bzahov.achievementsBG.constants.RegexPatterns.PATTERN_USERNAME;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(message = ERROR_USERNAME_INVALID, regexp = PATTERN_USERNAME)
    private String username;

    @NotBlank(message = ERROR_PASSWORD_MUST_NOT_BE_BLANK)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    @OneToOne(mappedBy = "userOfRowerID", cascade = CascadeType.ALL)
    @JsonBackReference
    private Rower rower;
}