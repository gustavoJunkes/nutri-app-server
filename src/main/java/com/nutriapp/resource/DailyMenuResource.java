package com.nutriapp.resource;

import com.nutriapp.domain.WeekDayEnum;
import com.nutriapp.dto.DailyMenuDto;
import com.nutriapp.service.dailymenu.DailyMenuService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/daily-menu")
@AllArgsConstructor
public class DailyMenuResource {

    private DailyMenuService dailyMenuService;

    @GetMapping()
    public ResponseEntity<List<DailyMenuDto>> list() {
          return new ResponseEntity<>(dailyMenuService.list(), HttpStatus.OK);
    }

    @GetMapping("/by-date")
    public ResponseEntity<DailyMenuDto> findByDate(@RequestParam("date") String date) {
        var toReturn = dailyMenuService.findByDate(LocalDate.parse(date));
        return new ResponseEntity(toReturn, toReturn != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getByWeekDay")
    public ResponseEntity<DailyMenuDto> getByWeekDay(@RequestParam String weekDay, @RequestParam String weeklyMenuId) {
        var toReturn = dailyMenuService.findByWeekDay(WeekDayEnum.valueOf(weekDay), weeklyMenuId);
        return new ResponseEntity(toReturn, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<DailyMenuDto> save(@RequestBody DailyMenuDto dailyMenu) {
        return new ResponseEntity<>(dailyMenuService.save(dailyMenu), HttpStatus.CREATED);
    }

}
