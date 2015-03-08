package com.example.kchal_000.eatright_uiuc;

import java.util.ArrayList;

/**
 * Created by Francisco RC on 3/8/2015.
 */
public class Menu extends Meal {

    ArrayList<Meal> mealList= new ArrayList<Meal>();

    public Menu(float f, float p, float c, String s, Meal m) {
        super(f, p, c, s);

        mealList.add(m);
    }

    public void update(){
        fiber=0;
        protein=0;
        calories=0;
        for (Meal meal : mealList) {
            fiber+=meal.getFiber();
            protein+=meal.getProtein();
            calories+=meal.getCalories();
        }
    }

    public void addMeal(Meal meal){
        if(!mealList.contains(meal)){
            mealList.add(meal);
            update();
        }
    }

    public void removeMeal(Meal meal){
        if(mealList.contains(meal)){
            mealList.remove(meal);
            update();
        }
    }
}
