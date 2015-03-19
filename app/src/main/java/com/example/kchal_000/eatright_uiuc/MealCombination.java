package com.example.kchal_000.eatright_uiuc;

import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by Francisco RC on 3/8/2015.
 */
public class MealCombination {

    ArrayList<Meal> mealList= new ArrayList<Meal>();
    float fiber = 0;          //x position of the point
    float protein = 0;          //y position of the point
    float calories = 0;
    float unitx, unity;
    ImageButton combine;
    boolean isCombining=false;

    public MealCombination(float f, float p, float c, Meal m) {
        fiber=f;
        protein=p;
        calories=c;
        mealList.add(m);
    }

    public MealCombination(){
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
        combine.setTranslationX((protein / calories) * unity);
        combine.setTranslationY((fiber / calories) * unitx);

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

    public void setCombine(){isCombining=true;}
    public void unsetCombine(){isCombining=false;}
    public boolean getCombine(){return  isCombining;}

    public ImageButton getImageButton(){return combine;}
    public void setImageButton(ImageButton ib, float ux, float uy){
        combine=ib;
        unitx=ux;
        unity=uy;
        combine.setTranslationX((protein / calories) * unity);
        combine.setTranslationY((fiber / calories) * unitx);
    }
}
