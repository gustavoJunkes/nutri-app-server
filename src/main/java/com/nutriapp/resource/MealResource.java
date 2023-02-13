package com.nutriapp.resource;

import com.nutriapp.domain.WeekDayEnum;
import com.nutriapp.dto.DailyMenuDto;
import com.nutriapp.dto.MealDto;
import com.nutriapp.service.dailymenu.DailyMenuService;
import com.nutriapp.service.meal.MealService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/meal")
@AllArgsConstructor
public class MealResource {

    private MealService mealService;

//    @GetMapping()
//    public ResponseEntity<List<MealDto>> list() {
//          return new ResponseEntity<>(mealService.list(), HttpStatus.OK);
//    }

    @PostMapping("/new")
    public ResponseEntity<MealDto> create(@RequestBody MealDto mealDto) {
        return new ResponseEntity<>(mealService.newMeal(mealDto), HttpStatus.CREATED);
    }

}
