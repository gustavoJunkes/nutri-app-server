package com.nutriapp.service.meal;

import com.nutriapp.domain.DailyMenu;
import com.nutriapp.dto.MealDto;

import java.util.List;

public interface MealService {

    List<MealDto> findByDailyMenu(DailyMenu dailyMenu);

    MealDto newMeal(MealDto meal);
}
