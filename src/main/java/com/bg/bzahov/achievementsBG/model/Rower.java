package com.bg.bzahov.achievementsBG.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Rower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = true)
    private String age;

    @NotBlank
    @Column(nullable = true)
    private String yearOfBirth;

    @OneToMany(mappedBy = "rowerID", cascade = CascadeType.REMOVE)
    private List<RowerIDCard> rowerIDCards = new ArrayList<>();

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userID")
//    private UserEntity userID;

    @OneToOne
    @JoinColumn(name = "userOfRowerID")
    @JsonManagedReference
    private UserEntity userOfRowerID;
}
