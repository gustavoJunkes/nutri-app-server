package com.nutriapp.service.mealFood;

import com.nutriapp.domain.DailyMenu;
import com.nutriapp.domain.Meal;
import com.nutriapp.domain.MealFood;
import com.nutriapp.dto.DailyMenuDto;
import com.nutriapp.dto.MealDto;
import com.nutriapp.repository.MealFoodRepository;
import com.nutriapp.repository.MealRepository;
import com.nutriapp.service.meal.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class MealFoodServiceImplement implements MealFoodService {

    @Autowired
    private MealFoodRepository mealFoodRepository;


    /**
     * Save the given MealFood.
     * If the given MealFood.food is not saved, will throw a transient exception
     * */
    public MealFood save(MealFood mealFood) {
        return mealFoodRepository.save(mealFood);
    }

}
