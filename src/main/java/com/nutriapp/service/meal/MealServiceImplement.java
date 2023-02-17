package com.nutriapp.service.meal;

import com.nutriapp.domain.DailyMenu;
import com.nutriapp.domain.Meal;
import com.nutriapp.domain.MealFood;
import com.nutriapp.dto.DailyMenuDto;
import com.nutriapp.dto.MealDto;
import com.nutriapp.repository.MealRepository;
import com.nutriapp.service.mealFood.MealFoodService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MealServiceImplement implements MealService {

    @Autowired
    private MealRepository mealRepository;

    private MealFoodService mealFoodService;

    public List<MealDto> findByDailyMenu(DailyMenu dailyMenu) {

        List<MealDto> mealDtos = new ArrayList<>();

        mealRepository.findAllByDailyMenu(dailyMenu).ifPresent(meals ->
               meals.forEach(meal -> {
                   mealDtos.add(MealDto.builder()
                                   .description(meal.getDescription())
                                   .mealPeriod(meal.getMealPeriod())
                                   .mealTime(meal.getMealTime())
                                   .mealFoods(meal.getMealFoods())
                                   .dailyMenu(DailyMenuDto.builder().id(dailyMenu.getId()).build())
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
                .description(mealDto.getDescription())
                .mealTime(mealDto.getMealTime())
                .mealFoods(mealDto.getMealFoods())
                .build();

        Optional.ofNullable(mealDto.getMealFoods()).ifPresent(mealFoods -> {
            mealFoods.forEach(mealFoodService::save);
        });

        meal = mealRepository.save(meal);

        return MealDto.builder()
                .dailyMenu(DailyMenuDto.builder()
                        .id(meal.getDailyMenu().getId()).build())
                .mealFoods(meal.getMealFoods())
                .mealTime(meal.getMealTime())
                .mealPeriod(meal.getMealPeriod())
                .id(meal.getId())
                .description(meal.getDescription())
                .build();
    }

}
