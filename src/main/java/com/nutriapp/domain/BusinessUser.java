package com.nutriapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_table")
@Getter
@Setter
public class BusinessUser {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    @Column(nullable = false, unique = true)
    private String login;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_food",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "food_id") }
    )
    private Set<Food> acceptedFoods;

    /* Represents how much calories of each the user needs in a day */
    private double dailyProtein;
    private double dailyCarbo;
    private double dailyFat;
    private double dailyCalories;
}
