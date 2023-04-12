package com.nutriapp.service.dailymenu;

import com.nutriapp.auth.AuthenticationFacade;
import com.nutriapp.auth.User;
import com.nutriapp.domain.DailyMenu;
import com.nutriapp.domain.WeekDayEnum;
import com.nutriapp.domain.WeeklyMenu;
import com.nutriapp.dto.DailyMenuDto;
import com.nutriapp.dto.WeeklyMenuDto;
import com.nutriapp.repository.DailyMenuRepository;
import com.nutriapp.service.meal.MealService;
import com.nutriapp.service.weeklymenu.WeeklyMenuService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class DailyMenuService {

    private DailyMenuRepository dailyMenuRepository;
    private MealService mealService;
    private WeeklyMenuService weeklyMenuService;

    private AuthenticationFacade authenticationFacade;

    public List<DailyMenuDto> list() {

        // TODO: 27/01/2023 implement

        return new ArrayList<>();
    }

    public DailyMenuDto findByDate(LocalDate date) {

        User user = (User) authenticationFacade.getAuthentication().getPrincipal();

        var dailyMenuOpt = dailyMenuRepository.findByDateAndUser(date, user);

        if (dailyMenuOpt.isPresent()) {
            return DailyMenuDto.builder()
                    .id(dailyMenuOpt.get().getId())
                    .date(dailyMenuOpt.get().getDate())
                    .weeklyMenu(WeeklyMenuDto.builder().id(dailyMenuOpt.get().getWeeklyMenu().getId()).build())
                    .build();
        } else
            return null;
    }

    /**
     * Find the DailyMenu by week day and WeeklyMenu
     * */
    public DailyMenuDto findByWeekDay(WeekDayEnum weekDay, String weeklyMenuId) {
        User user = (User) authenticationFacade.getAuthentication().getPrincipal();

        // find current WeeklyMenu
        val weeklyMenu = weeklyMenuService.findById(weeklyMenuId);

        if (Objects.isNull(weeklyMenu))
            throw new IllegalArgumentException("No weekly menu found");

        var dailyMenu =  dailyMenuRepository.getByWeekDayAndAndWeeklyMenuAndUser(weekDay, UUID.fromString(weeklyMenuId), user.getId());

        if (dailyMenu.isEmpty()) {
            dailyMenu = Optional.of(newDailyMenu(weekDay, weeklyMenu));
        }

        val meals = mealService.findByDailyMenu(dailyMenu.get());

        return DailyMenuDto.builder()
                .id(dailyMenu.get().getId())
                .date(dailyMenu.get().getDate())
                .weekDay(dailyMenu.get().getWeekDay())
                .meals(meals)
                .weeklyMenu(WeeklyMenuDto.builder().id(dailyMenu.get().getWeeklyMenu().getId()).build())
                .build();
    }

    public DailyMenuDto save(DailyMenuDto dailyMenu) {
        var createdDalyMenu = dailyMenuRepository.save(
                DailyMenu.builder()
                        .id(dailyMenu.getId())
                        .weeklyMenu(WeeklyMenu.builder().id(dailyMenu.getWeeklyMenu().getId()).build())
                        .weekDay(dailyMenu.getWeekDay())
                        .date(dailyMenu.getDate())
                .build());

        dailyMenu.setId(createdDalyMenu.getId());

        return dailyMenu;
    }

    private DailyMenu newDailyMenu(WeekDayEnum weekDay, WeeklyMenuDto weeklyMenu) {

        var date = weeklyMenu.getBeginDate().plusDays(DayOfWeek.valueOf(weekDay.name()).getValue() - 1); // The day of month of the day of week, based on the start date of week.

        val dailyMenu = DailyMenu.builder()
                .weekDay(weekDay)
                .weeklyMenu(WeeklyMenu.builder().id(weeklyMenu.getId()).build())
                .date(date)
                .build();
        return dailyMenuRepository.save(dailyMenu);
    }
}
