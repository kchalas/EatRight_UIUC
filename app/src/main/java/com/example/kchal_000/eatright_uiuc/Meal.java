package com.example.kchal_000.eatright_uiuc;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageButton;
import android.view.View;

/**
 * Created by Francisco RC on 2/20/2015.
 */
public class Meal {

    float fiber = 0;          //x position of the point
    float protein = 0;          //y position of the point
    float calories = 0;
    String id = "";

    public Meal(float f, float p, float c, String s) {
        protein = p;
        fiber = f;
        calories = c;
        id = s;
    }

    public float getFiber() {
        return fiber;
    }

    public float getProtein() {
        return protein;
    }

    public float getCalories() {
        return calories;
    }

    public String getId() {
        return id;
    }

    public boolean equals(Meal meal) {

        if (calories == meal.calories && fiber == meal.fiber && protein == meal.protein && id == meal.id)
            return true;
        return false;
    }
}
