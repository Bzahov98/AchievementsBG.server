package com.bg.bzahov.achievementsBG.model;

import com.bg.bzahov.achievementsBG.model.validators.ValidGender;
import com.bg.bzahov.achievementsBG.model.validators.ValidYearOfBirth;
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

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.*;

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

    @ValidGender(message = ERROR_INVALID_GENDER)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Min(value = 1, message = ERROR_AGE_RESTRICTION)
    @Max(value = 99, message = ERROR_AGE_RESTRICTION)
    @Column(nullable = true)
    private Integer age;

    @ValidYearOfBirth(message = ERROR_YEAR_OF_BIRTH_RESTRICTION)
    @Column(nullable = true)
    private Integer yearOfBirth;

    @OneToMany(mappedBy = "rower", cascade = CascadeType.REMOVE)
    @JsonIgnore // Add this annotation to break the circular reference
    private List<RowerIDCard> rowerIDCards;

    @OneToOne
    @JoinColumn(name = "userOfRowerID")
    @JsonManagedReference
    private UserEntity userOfRowerID;
}
