package com.bg.bzahov.achievementsBG.model;

import com.bg.bzahov.achievementsBG.constants.ErrorConstants;
import com.bg.bzahov.achievementsBG.model.validators.YearOfBirth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @Min(value = 1, message = ErrorConstants.ERROR_AGE_RESTRICTION)
    @Max(value = 99, message = ErrorConstants.ERROR_AGE_RESTRICTION)
    @Column(nullable = true)
    private int age;

    @YearOfBirth(message = ErrorConstants.ERROR_YEAR_OF_BIRTH_RESTRICTION)
    @Column(nullable = true)
    private int yearOfBirth;

//    @OneToMany(mappedBy = "rowerID", cascade = CascadeType.REMOVE)
//    private List<RowerIDCard> rowerIDCards = new ArrayList<>();

    @OneToMany(mappedBy = "rower", cascade = CascadeType.REMOVE)
    @JsonIgnore // Add this annotation to break the circular reference
    private List<RowerIDCard> rowerIDCards;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userID")
//    private UserEntity userID;

    @OneToOne
    @JoinColumn(name = "userOfRowerID")
    @JsonManagedReference
    private UserEntity userOfRowerID;
}
