package com.nutriapp.resource;

import com.nutriapp.domain.WeeklyMenu;
import com.nutriapp.dto.WeeklyMenuDto;
import com.nutriapp.service.weeklymenu.WeeklyMenuService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/weekly-menu")
@AllArgsConstructor
public class WeeklyMenuResource {

    private WeeklyMenuService weeklyMenuService;

    @GetMapping()
    @PreAuthorize("hasAnyRole='*'")
    public ResponseEntity<List<WeeklyMenuDto>> list(@RequestParam ("userId") String userId, @RequestParam("inDate") LocalDate inDate) {
          return new ResponseEntity<>(weeklyMenuService.list(userId, inDate), HttpStatus.OK);
    }

    @GetMapping("/by-date")
    public ResponseEntity<WeeklyMenuDto> findByDate(@RequestParam("date") String date) {
        var toReturn = weeklyMenuService.findByDate(LocalDate.parse(date));
        return new ResponseEntity(toReturn, toReturn != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PostMapping("/new")
    public ResponseEntity<WeeklyMenuDto> create(@RequestBody WeeklyMenuDto weeklyMenu) {
        return new ResponseEntity<>(weeklyMenuService.create(weeklyMenu), HttpStatus.CREATED);
    }

}
