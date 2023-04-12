package com.nutriapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String description;
    private double caloriesPerGram;
    private double proteins;
    private double fat;
    private double carbo;

    @Enumerated(EnumType.STRING)
    private UnityEnum unity;

    @Enumerated(EnumType.STRING)
    DayTimeEnum bestEatPeriod;

}
