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
    public double calculateCaloricExpeditureInCardio(double speed, double minutes, double weight) {
        double toReturn = 0;

        // calculate how much will spend in a minute
        double spentPerMinute = calculateCaloriesSpendPerMinuteCardio(speed, weight); // needs to search

        // multiply by minutes
        toReturn = spentPerMinute * minutes;

        return toReturn;
    }

    /**
     * Calculate how much calories are spent in a minute considering the given speed (in km/h) and the weight (in kg)
     * */
    public double calculateCaloriesSpendPerMinuteCardio(double speed, double weight) {
        double toReturn = 0;

        toReturn = speed * weight * 0.0175;

        return toReturn;
    }

    /**
     * Calculate how much cardio needs to do with the given intensity (in km/h) to spend the given calories
     * */
    public double calculateCardioForCaloriesAndIntensity(double calories, double speed /*in km/h*/, double weight) {
        double toReturn = (speed * weight * 0.0175) / calories;
        return toReturn;
    }

    /**
     * Calculate how much cardio needs to do with the list of intensities to spend the given calories.
     *
     * @returns a hashMap with the key -> intensity and the value -> time of cardio
     * */
    public Map<Double, Double> calculateCardioForCalories(double calories, double[] speeds, double weight) {
        Map<Double, Double> toReturn = new HashMap<>();

        for (int i = 0; i < speeds.length; i++) {
           double timeOfCardio = this.calculateCardioForCaloriesAndIntensity(calories, speeds[i], weight);
           toReturn.put(speeds[i], timeOfCardio);
        }

        return toReturn;
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
