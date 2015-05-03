package com.example.kchal_000.eatright_uiuc;

import android.media.Image;
import android.view.ViewGroup;
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
    int size=40;
    ImageButton combine;
    ImageButton ib;
    boolean isCombining=false;

    public MealCombination(float f, float p, float c, Meal m) {
        fiber=f;
        protein=p;
        calories=c;
        mealList.add(m);
        sizeFromCal();
    }

    public MealCombination(Meal m) {
        fiber=m.getFiber();
        protein=m.getProtein();
        calories=m.getCalories();
        mealList.add(m);
        sizeFromCal();
    }

    public MealCombination(){
    }

    public float mealCombinationGetFiber(MealCombination comb) {

        return comb.fiber;
    }

    public float mealCombinationGetProtein(MealCombination comb) {

        return comb.protein;
    }

    public float mealCombinationGetCalories(MealCombination comb) {

        return comb.calories;
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
        sizeFromCal();
        ViewGroup.LayoutParams params = combine.getLayoutParams();
        params.height=size;
        params.width=size;
        combine.setLayoutParams(params);
        combine.setTranslationX(posx-(size/2));
        combine.setTranslationY(posy - (size / 2));
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

    public void setIcon(ImageButton imageButton){
        ib=imageButton;
    }

    public void setCombine(){
        isCombining=true;
        ib.setBackgroundResource(R.drawable.combinetoggle);
    }
    public void unsetCombine(){
        isCombining=false;
        ib.setBackgroundResource(R.drawable.combine);
    }
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

    void sizeFromCal(){
        int change= (int) (0.08*(calories%250));
        switch((int)calories/250){
            case 0: size=40+change; break; //low cal
            case 1: size=60+change; break; //med cal
            case 2: size=80+change; break; //high cal
            default: size=110; break;//very high cal
        }
    }
    public int getSize(){return size;}
}
