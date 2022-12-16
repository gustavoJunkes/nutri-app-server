package com.nutriapp.repository;

import com.nutriapp.domain.Food;
import com.nutriapp.domain.MealFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealFoodRepository extends JpaRepository<MealFood, Long> {

}
