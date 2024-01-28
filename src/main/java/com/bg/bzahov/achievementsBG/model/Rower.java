package com.bg.bzahov.achievementsBG.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table//
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter//
@Setter//
@ToString//
public class Rower {

    @Id
    @SequenceGenerator(
            name = "rower_sequence",
            sequenceName = "rower_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            generator = "rower_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private Integer age;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

//    public Rower(Long id, String name, Integer age, Gender gender) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.gender = gender;
//    }
    public Rower(Long id, String name, Integer age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = Gender.valueOf(gender);
    }
}