package com.nutriapp.nutrition.utils;

import org.springframework.stereotype.Service;

/**
 * Contains all the methods and logic to attend to demmands related to nutrition.
 * This class should not contain any business logic, only the nutrition related logic
 * */
@Service
public class NutitionUtils {


    /**
     * Calculate how much calories should be injested in a day based on how much is spent and the objective
     * */
    public void calculateTotalCaloriesShouldBeInjested(double totalCaloriesExpenditure, String kindOfDietEnum /*enum.growth, enum.dry*/) {

    }

    public void calculateHowMuchProteinShouldConsume() {

    }

    public void calculateHowMuchFatShouldConsume() {

    }

    public void calculateHowMuchCarbohydrateShouldConsume() {

    }

    /**
     * Calculate how much of each kind of calorie should be consumed, considering the biotype. Use the other dedicated methods for this.
     * */
    public void calculateProteinFatAndCarbohydrate(String biotypeEnum) {

    }

    /**
     * Separate the calories needed in the day into foods and then into meals, respecting the accepted foods per user
     * */
    public void processDailyMeals(double caloriesSpent, int numberOfMeals, String[] acceptedFoods) {

    }
}
