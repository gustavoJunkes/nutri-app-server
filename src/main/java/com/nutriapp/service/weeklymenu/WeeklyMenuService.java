package com.nutriapp.service.weeklymenu;

import com.nutriapp.auth.AuthenticationFacade;
import com.nutriapp.auth.User;
import com.nutriapp.domain.WeeklyMenu;
import com.nutriapp.dto.WeeklyMenuDto;
import com.nutriapp.repository.WeeklyMenuRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class WeeklyMenuService {

    private WeeklyMenuRepository weeklyMenuRepository;
    private AuthenticationFacade authenticationFacade;

    public List<WeeklyMenuDto> list(String userId, LocalDate inDate) {

        // TODO: 27/01/2023 implement

        return new ArrayList<>();
    }

    public WeeklyMenuDto findByDate(LocalDate date) {
        User user = (User) authenticationFacade.getAuthentication().getPrincipal();

        var weeklyMenuOpt = weeklyMenuRepository.findFirstByBeginDateLessThanEqualAndEndDateGreaterThanEqualAndUser(date, date, user); //(newWithDateAndUser(date, user));

        if (weeklyMenuOpt.isEmpty()) {
            weeklyMenuOpt = Optional.of(newWithDateAndUser(date, user));
        }

        return WeeklyMenuDto.builder()
                .id(weeklyMenuOpt.get().getId())
                .user(weeklyMenuOpt.get().getUser())
                .beginDate(weeklyMenuOpt.get().getBeginDate())
                .endDate(weeklyMenuOpt.get().getEndDate())
                .build();
    }

    public WeeklyMenuDto create(WeeklyMenuDto weeklyMenu) {

        // TODO: 28/01/2023 add validations to date, e.g interval of x days, end bigger than begin, begin is a monday, end is a sunday

        WeeklyMenu createdWeeklyMenu = weeklyMenuRepository.save(WeeklyMenu.builder()
                        .user(weeklyMenu.getUser())
                        .beginDate(weeklyMenu.getBeginDate())
                        .endDate(weeklyMenu.getEndDate())
                .build());

        return WeeklyMenuDto.builder()
                .id(createdWeeklyMenu.getId())
                .user(createdWeeklyMenu.getUser())
                .beginDate(createdWeeklyMenu.getBeginDate())
                .endDate(createdWeeklyMenu.getEndDate())
                .build();
    }

    public WeeklyMenuDto findById(String id) {
        val weeklyMenu = weeklyMenuRepository.findById(UUID.fromString(id)).orElse(null);

        if (Objects.isNull(weeklyMenu)) {
            return null;
        }

        return WeeklyMenuDto.builder()
                .id(weeklyMenu.getId())
                .beginDate(weeklyMenu.getBeginDate())
                .endDate(weeklyMenu.getEndDate())
                .user(weeklyMenu.getUser())
                .build();
    }

    /**
     * Generate a new Weekly menu for the given user,
     * and with the begin and final corresponding to monday and sunday
     * */
    private WeeklyMenu newWithDateAndUser(LocalDate date, User user) {
        DayOfWeek day = date.getDayOfWeek();
        LocalDate beginDate;

        if (day.getValue() == DayOfWeek.MONDAY.getValue()) {
            beginDate = date;
        } else {
            beginDate = date.minusDays(day.getValue() - DayOfWeek.MONDAY.getValue());
        }

        LocalDate finalDate = beginDate.plusDays(DayOfWeek.SATURDAY.getValue());


        WeeklyMenu weeklyMenu = WeeklyMenu.builder()
                .user(user)
                .beginDate(beginDate)
                .endDate(finalDate)
                .build();

        weeklyMenu = weeklyMenuRepository.save(weeklyMenu);

        return weeklyMenu;
    }

}
