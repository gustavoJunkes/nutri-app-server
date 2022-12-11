package com.nutriapp.nutrition.utils;

import com.nutriapp.domain.DayTimeEnum;
import com.nutriapp.domain.Food;
import com.nutriapp.domain.Meal;
import com.nutriapp.domain.MealFood;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Contains all the methods and logic related to nutrition.
 * This class should not contain any business logic, only the nutrition related logic
 * */
@Service
public class NutitionUtils {


    // TODO: 19/12/2022 O sistema deve calcular quantas calorias diárias o usuário
    //  deve consumir.
    //  Depois, quanto de gordura, proteína e carboidrato.
    //  Depois, separar essas gorduras, proteinas e carboidratos em um número de refeições.
    //  A partir disso, deve escolher alguns alimentos (dentre os selecionados pelo usuário) para montar a refeição.
    //  Depois, calcular quanto de cada um é necessário para bater a proteína. Depois o mesmo para gordura e carboidrato.
    //  Nesse momento temos cada refeição com seus devidos alimentos e quantidades.
    //  Um dos problemas que devem surgir deve ser a combinação esquista de comidas, como por exemplo sorvete e arroz.
    //  Outro problema é a divisão igual de quantidade entre cada alimento.
    //  Vai ser um desafio combinar os alimentos em quantidade certa de forma a bater a quantidade ideal de cada coisa (prote, carbo e gord) sem extrapolar nada
















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
    /**
     *  Separate de daily calories into meals, considering the given number of meals
     * */
    public double separateCaloriesInMeals(double dailyCalories, int numberOfMeals) {
        double caloriesPerMeal = dailyCalories / numberOfMeals;

        return caloriesPerMeal;
    }

    /**
     * Process each meal of the day, considering the given calories, desired foods and ideal period for each meal
     * */
    public List<Meal> processEachDailyMeal(double caloriesPerMeal, int numberOfMeals, List<Food> desiredFoods, double protein, double fat, double cardio) {
        // TODO: 10/12/2022 consider needed protein, fat and carbo in the meals

        List<DayTimeEnum> mealsPeriod =  processMealTime(numberOfMeals);
        List<Meal> dailyMeals = new ArrayList<>();

        for(int i = 0; i < numberOfMeals; i++) {
            Meal meal = new Meal();
            meal.setMealPeriod(mealsPeriod.get(i));
            processDesiredFoodsInMealFoods(desiredFoods, caloriesPerMeal, meal);
            dailyMeals.add(meal);
        }
        return dailyMeals;
    }

    /**
     * Returns an ordenated list of periods for each meal, based on the number of meals in a day
     * */
    public List<DayTimeEnum> processMealTime(int numberOfMeals) {
        List<DayTimeEnum> mealsPeriod = new ArrayList<>();
        switch (numberOfMeals) {
            case 3:
                mealsPeriod = List.of(DayTimeEnum.BREAKFAST, DayTimeEnum.LUNCH, DayTimeEnum.DINNER);
                break;
            case 4:
                mealsPeriod = List.of(DayTimeEnum.BREAKFAST, DayTimeEnum.LUNCH, DayTimeEnum.AFTERNOON_EARLY, DayTimeEnum.DINNER);
                break;
            case 5:
                mealsPeriod = List.of(DayTimeEnum.BREAKFAST, DayTimeEnum.MORNING_EARLY, DayTimeEnum.LUNCH, DayTimeEnum.AFTERNOON_LATE, DayTimeEnum.DINNER);
                break;
            case 6:
                mealsPeriod = List.of(DayTimeEnum.BREAKFAST, DayTimeEnum.MORNING_EARLY, DayTimeEnum.LUNCH, DayTimeEnum.AFTERNOON_EARLY, DayTimeEnum.AFTERNOON_LATE, DayTimeEnum.DINNER);
                break;
            case 7:
                mealsPeriod = List.of(DayTimeEnum.BREAKFAST, DayTimeEnum.MORNING_EARLY, DayTimeEnum.MORNING_LATE, DayTimeEnum.LUNCH, DayTimeEnum.AFTERNOON_EARLY, DayTimeEnum.AFTERNOON_LATE, DayTimeEnum.DINNER);
                break;
            case 8:
                mealsPeriod = List.of(DayTimeEnum.BREAKFAST, DayTimeEnum.MORNING_EARLY, DayTimeEnum.MORNING_LATE, DayTimeEnum.LUNCH, DayTimeEnum.AFTERNOON_EARLY, DayTimeEnum.AFTERNOON_LATE, DayTimeEnum.NIGHT_EARLY, DayTimeEnum.DINNER);
                break;
            default:
                throw new IllegalArgumentException("Invalid number of meals in a day");
        }
        return mealsPeriod;
    }

    /**
     * Separate the foods for the given meal in meal foods, considering the day period
     * */
    public void processDesiredFoodsInMealFoods(List<Food> foods, double caloriesPerMeal, Meal meal) {
        // verify time of the day of the meal, and based on thad choose the more adequated foods.
        // choose max of 3 fruits and separate them equaly based on calories.

        List<Food> foodsForMeal = new ArrayList<>();

        switch (meal.getMealPeriod()) {
            case MORNING_EARLY:
                foodsForMeal = getBestFoodsByPeriod(foods, DayTimeEnum.MORNING_EARLY);
                break;
            case BREAKFAST:
                foodsForMeal = getBestFoodsByPeriod(foods, DayTimeEnum.BREAKFAST);
                break;
            case MORNING_LATE:
                foodsForMeal = getBestFoodsByPeriod(foods, DayTimeEnum.MORNING_LATE);
                break;
            case LUNCH:
                foodsForMeal = getBestFoodsByPeriod(foods, DayTimeEnum.LUNCH);
                break;
            case AFTERNOON_EARLY:
                foodsForMeal = getBestFoodsByPeriod(foods, DayTimeEnum.AFTERNOON_EARLY);
                break;
            case AFTERNOON_LATE:
                foodsForMeal = getBestFoodsByPeriod(foods, DayTimeEnum.AFTERNOON_LATE);
                break;
            case NIGHT_EARLY:
                foodsForMeal = getBestFoodsByPeriod(foods, DayTimeEnum.NIGHT_EARLY);
                break;
            case DINNER:
                foodsForMeal = getBestFoodsByPeriod(foods, DayTimeEnum.DINNER);
                break;
            case NIGHT_LATE:
                foodsForMeal = getBestFoodsByPeriod(foods, DayTimeEnum.NIGHT_LATE);
                break;
        }
        // choose the best 3 foods
        foodsForMeal = foodsForMeal.size() >= 3 ? foodsForMeal.subList(0, 3) : foodsForMeal.subList(0, foodsForMeal.size() - 1);

        // separate them in calories
        double caloriesForFood = caloriesPerMeal / foodsForMeal.size();

        Set<MealFood> mealFoods = new HashSet<>();

        foodsForMeal.forEach(food -> {
            MealFood mealFood = new MealFood(food, caloriesForFood);
            mealFoods.add(mealFood);
        });
        // TODO: 10/12/2022 persist the meal foods in database

        meal.setMealFoods(mealFoods);

        // TODO: 10/12/2022 persist the meal in database
    }

    /**
     * Filter the foods list based on the given day Period
     * */
    public List<Food> getBestFoodsByPeriod(List<Food> foods, DayTimeEnum dayPeriod) {
        List<Food> foodsToReturn = new ArrayList<>();

        for(Food food: foods) {
            if (food.getBestEatPeriod().equals(dayPeriod))
                foodsToReturn.add(food);
        }
        return foodsToReturn;
    }
}
