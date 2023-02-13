package com.nutriapp.service.meal;

import com.nutriapp.domain.DailyMenu;
import com.nutriapp.domain.Meal;
import com.nutriapp.domain.MealFood;
import com.nutriapp.dto.DailyMenuDto;
import com.nutriapp.dto.MealDto;
import com.nutriapp.repository.MealRepository;
import com.nutriapp.service.mealFood.MealFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class MealServiceImplement implements MealService {

    @Autowired
    private MealRepository mealRepository;

    private MealFoodService mealFoodService;

    public List<MealDto> findByDailyMenu(DailyMenu dailyMenu) {

        List<MealDto> mealDtos = Collections.emptyList();

        mealRepository.findAllByDailyMenu(dailyMenu).ifPresent(meals ->
               meals.forEach(meal -> {
                   mealDtos.add(MealDto.builder()
                                   .mealPeriod(meal.getMealPeriod())
                                   .mealTime(meal.getMealTime())
                                   .mealFoods(meal.getMealFoods())
                           .build());
               }));
        return mealDtos;
    }

    /**
     * Create a new meal in a given DailyMenu and with the given properties.
     * If a MealFood does not exist, create a new one with the given properties.
     *
     * @param mealDto
     *
     * @return the new meal
     * */
    public MealDto newMeal(MealDto mealDto) {
        Meal meal = Meal.builder()
                .dailyMenu(DailyMenu.builder()
                        .id(mealDto.getDailyMenu().getId()).build())
                .mealPeriod(mealDto.getMealPeriod())
                .mealTime(mealDto.getMealTime())
                .mealFoods(mealDto.getMealFoods())
                .build();

        mealDto.getMealFoods().forEach(mealFoodService::save);

        meal = mealRepository.save(meal);

        return MealDto.builder()
                .dailyMenu(DailyMenuDto.builder()
                        .id(meal.getDailyMenu().getId()).build())
                .mealFoods(meal.getMealFoods())
                .mealTime(meal.getMealTime())
                .mealPeriod(meal.getMealPeriod())
                .build();
    }

}
