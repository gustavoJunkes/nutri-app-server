package com.nutriapp.dto;

import com.nutriapp.domain.DayTimeEnum;
import com.nutriapp.domain.MealFood;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealDto {

    private UUID id;
    private String description;
    private Set<MealFood> mealFoods;
    private LocalTime mealTime;
    private DayTimeEnum mealPeriod;
    private DailyMenuDto dailyMenu;

}
