package com.nutriapp.dto;

import com.nutriapp.auth.User;
import com.nutriapp.domain.WeekDayEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DailyMenuDto {

    private UUID id;

    private WeeklyMenuDto weeklyMenu;

    private LocalDate date;

    private WeekDayEnum weekDay;

    private List<MealDto> meals;



}
