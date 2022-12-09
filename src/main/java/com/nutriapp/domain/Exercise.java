package com.nutriapp.domain;

import jakarta.persistence.Entity;

@Entity
public class Exercise {
    String description;
    double intensity;
    double time;
}
