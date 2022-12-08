package com.nutriapp.caloricexpenditure.utils;

import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public Map<double, double> calculateCardioForCalories(double calories, double intensity /*in km per minute*/) {
        return new HashMap<double, double>();
    }

    /**
     * Calculate the daily basal caloric expenditure using the known formula
     *
     * @returns the daily basal caloric expenditure just for existing
     * */
    public void calculateDailyBasalCaloricExpenditure(boolean isMale, double weight, double height) {

    }

    /**
     * Calculate the daily total caloric expenditure using the known formula and considering other activities that spend energy
     * */
    public void calculateDailyTotalCaloricExpenditure(boolean isMale, double weight, double height) {

    }

    /**
     * Process which and how much exercises to spend the given calories, respecting the accepted exercises list
     * */
    public void processExercisesToSpendGivenCalories(double calories, String[] acceptedExercises) {

    }
}
