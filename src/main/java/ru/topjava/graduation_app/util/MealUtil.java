package ru.topjava.graduation_app.util;

import ru.topjava.graduation_app.entity.Meal;
import ru.topjava.graduation_app.to.MealTo;

import java.util.ArrayList;
import java.util.List;

public class MealUtil {
    public static List<MealTo> getTos(List<Meal> meals) {
        List<MealTo> mealToList = new ArrayList<>();
        if (meals == null || meals.isEmpty()) {
            return mealToList;
        }
        for (Meal m : meals) {
            mealToList.add(new MealTo(m));
        }
        return mealToList;
    }
}
