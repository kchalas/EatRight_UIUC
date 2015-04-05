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
        float posx=0,posy=0;

        fiber=0;
        protein=0;
        calories=0;
        for (Meal meal : mealList) {
            fiber+=meal.getFiber();
            protein+=meal.getProtein();
            calories+=meal.getCalories();
        }
        posx=(protein / calories) * unity * 100;
        posy=(fiber / (calories/500)) * unitx;
        combine.setTranslationX(posx-37.5f);
        combine.setTranslationY(posy-37.5f);

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

    public void addDropMeal(Meal meal){
        if(!mealList.contains(meal)){
            mealList.add(meal);
        }else{
            mealList.remove(meal);
        }
        update();
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

    public boolean isCombined(Meal meal){
        if(mealList.contains(meal)){
            return true;
        }
        return false;
    }

    public String toString(){
        String name="";

        for (Meal meal : mealList) {
                name+=meal.getId()+" and ";
        }

        name=name.substring(0,(name.length()-5));

        return name+'/'+calories+'/'+fiber+'/'+protein;
    }
    public void fromString(String string){
        String[] data=string.split("/");
        calories=Float.valueOf(data[0]);
        fiber=Float.valueOf(data[1]);
        protein=Float.valueOf(data[2]);
    }
}
