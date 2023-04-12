package com.nutriapp.service.food;

import com.nutriapp.domain.Food;
import com.nutriapp.dto.FoodDto;

import java.util.List;

public interface FoodService {

    FoodDto save(FoodDto foodDto);

    List<FoodDto> list();

}
