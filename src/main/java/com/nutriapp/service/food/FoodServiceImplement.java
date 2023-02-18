package com.nutriapp.service.food;

import com.nutriapp.domain.DailyMenu;
import com.nutriapp.domain.Food;
import com.nutriapp.domain.Meal;
import com.nutriapp.dto.DailyMenuDto;
import com.nutriapp.dto.FoodDto;
import com.nutriapp.dto.MealDto;
import com.nutriapp.repository.FoodRepository;
import com.nutriapp.repository.MealRepository;
import com.nutriapp.service.meal.MealService;
import com.nutriapp.service.mealFood.MealFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImplement implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public FoodDto save(FoodDto foodDto) {

        Food food = Food.builder()
                .description(foodDto.getDescription())
                .build();

        food = foodRepository.save(food);

        foodDto.setId(String.valueOf(food.getId()));

        return foodDto;
    }
}
