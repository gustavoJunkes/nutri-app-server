package com.nutriapp.caloricexpenditure.utils;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains all the methods and logic related to calories expenditure.
 * This class should not contain any business logic, only the calories expenditure related logic
 * */
@Service
public class CaloriesExpenditureUtils {

    /**
     * Calculate how much calories in a cardio exercise
     * @returns the calories spent in the given cardio
     * */
    public double calculateCaloricExpeditureInCardio(double kmPerMinute, double minutes) {
        double toReturn = 0;

        // search how much will spend in a minute
        double spentPerMinute = 10; // needs to search

        // multiply by minutes
        toReturn = spentPerMinute * minutes;

        return toReturn;
    }

    /**
     * Calculate how much cardio needs to do with the given intensity to spend the given calories
     * */
    public void calculateCardioForCaloriesAndIntensity(double calories, double intensity /*in km per minute*/) {
        
    }

    /**
     * Calculate how much cardio needs to do with the list of intensities to spend the given calories.
     *
     * @returns a map with the key -> calories and the value -> intensity
     * */
    public Map<Double, Double> calculateCardioForCalories(double calories, double intensity /*in km per minute*/) {
        return new HashMap<Double, Double>();
    }

    /**
     * Calculate the daily basal caloric expenditure using the known formula
     *
     * @returns the daily basal caloric expenditure just for existing
     * */
    public double calculateDailyBasalCaloricExpenditure(boolean isMale, double weight, double height, double age) {
        if (isMale) {
            return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            return 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
    }

    /**
     * Calculate the daily total caloric expenditure using the known formula and considering other activities that spend energy
     * */
    public void calculateDailyTotalCaloricExpenditure(boolean isMale, double weight, double height, double age, List<String> activities /*activities in the day*/) {
        double bmr = this.calculateDailyBasalCaloricExpenditure(isMale, weight, height, age);

        // calcular outros gastos de energia

        // somar ao restante
    }

    /**
     * Process which and how much exercises are necessary to spend the given calories, respecting the accepted exercises list
     * */
    public void processExercisesToSpendGivenCalories(double calories, String[] acceptedExercises) {

    }
}
