package com.example.kchal_000.eatright_uiuc;

import android.media.Image;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import information.MenuItem;

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

    public MealCombination(Meal m) {
        fiber=m.getFiber();
        protein=m.getProtein();
        calories=m.getCalories();
        mealList.add(m);
        sizeFromCal();
    }

    public MealCombination(){
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

    public String getId(){
        String name="";

        for (Meal meal : mealList) {
            name+=meal.getId()+" and ";
        }
        if(name.length()>5){
            name=name.substring(0,(name.length()-5));
        }
        return name;
    } // creates a string with the names of all the meals in the mealList

    public void update(){
        float posx=0,posy=0;

        fiber=0;
        protein=0;
        calories=0;
        for (Meal meal : mealList) {
            fiber+=meal.getFiber();                 //updates fiber
            protein+=meal.getProtein();             //proteing
            calories+=meal.getCalories();           //and calories
        }
        posx=(protein / calories) * unity * 100;    //recalculates its possition
        posy=(fiber / (calories/500)) * unitx;
        sizeFromCal();
        ViewGroup.LayoutParams params = combine.getLayoutParams();
        params.height=size;                         //updates its size
        params.width=size;
        combine.setLayoutParams(params);
        if(mealList.size()>1){
            combine.setTranslationX(posx-(size/2)); //if more than one element in the mealList
            combine.setTranslationY(posy-(size/2)); //moves the point to the correct position(correcting for its size)
        }else{
            combine.setTranslationX(0-(size/2));    //if only one element in the mealList
            combine.setTranslationY(0-(size/2));    //moves the point to allow unselecting it
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

        name=getId();

        return name+'/'+calories+'/'+fiber+'/'+protein;
    }
    public void fromString(String string){
        String[] data=string.split("/");
        calories=Float.valueOf(data[1]);
        fiber=Float.valueOf(data[2]);
        protein=Float.valueOf(data[3]);
    }

    void sizeFromCal(){
        int change= (int) (0.08*(calories%250));
        switch((int)calories/250){
            case 0: size=40+change; break; //low cal
            case 1: size=60+change; break; //med cal
            case 2: size=80+change; break; //high cal
            default: size=110; break;//very high cal
        }
    }               //changes size depending on calories
    public int getSize(){return size;}

    public ArrayList<Meal> getMealList(){return mealList;}
    public void setMealList(ArrayList<MenuItem> meallist){
        for(information.MenuItem item: meallist){
            mealList.add(new Meal(item));
        }
    }                                       //sets the list if ImageButtons aren't important
    public void setFlawlessMealList(ArrayList<MenuItem> combList, ArrayList<Meal> pointList){
        for(information.MenuItem item: combList){
           for(Meal meal: pointList) {
                if(meal.equals(item)) {
                    mealList.add(meal);
                    meal.getImageButton().setBackgroundResource(R.drawable.pointoutcomb);
                    break;
                }
           }
        }
    }    //sets the list if ImageButtons are important
}
