package com.nutriapp.resource;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/food")
@RestController
@AllArgsConstructor
public class FoodResource {

    @GetMapping
    public ResponseEntity<String> demo() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }


}
