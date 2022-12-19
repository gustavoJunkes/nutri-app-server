package com.nutriapp.repository;

import com.nutriapp.domain.Food;
import com.nutriapp.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

}
