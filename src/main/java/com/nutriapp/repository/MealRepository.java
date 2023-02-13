package com.nutriapp.repository;

import com.nutriapp.domain.DailyMenu;
import com.nutriapp.domain.Food;
import com.nutriapp.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    Optional<List<Meal>> findAllByDailyMenu(DailyMenu dailyMenu);

}
