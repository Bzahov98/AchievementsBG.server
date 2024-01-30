package com.bg.bzahov.achievementsBG.model;

//import jakarta.persistence.*;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RowerIDCard {

    @Id
    @SequenceGenerator(
            name = "rowerID_sequence",
            sequenceName = "rower_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            generator = "rowerID_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Rower rower;

    @Column( unique = true, nullable = false)
    private String cardNumber;
}