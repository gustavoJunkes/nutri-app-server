package com.nutriapp.resource;

import com.nutriapp.dto.FoodDto;
import com.nutriapp.service.food.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/food")
@RestController
@AllArgsConstructor
public class FoodResource {

   private FoodService foodService;

   @PostMapping("/save")
   public ResponseEntity<FoodDto> save(@RequestBody FoodDto foodDto) {
      return new ResponseEntity<>(foodService.save(foodDto), HttpStatus.CREATED);
   }

}
