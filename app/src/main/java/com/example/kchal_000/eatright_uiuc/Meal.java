package com.example.kchal_000.eatright_uiuc;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageButton;
import android.view.View;

import information.MenuItem;
import information.NutritionInfo;

/**
 * Created by Francisco RC on 2/20/2015.
 */
public class Meal {

    float fiber = 0;          //x position of the point
    float protein = 0;          //y position of the point
    float calories = 0;
    String id = "";
    String source="";
    ImageButton ib;

    public Meal(float f, float p, float c, String s) {
        protein = p;
        fiber = f;
        calories = c;
        id = s;
    }

    public Meal(MenuItem item){
        NutritionInfo nut;

        id=item.getName();
        source=item.getRestaurantName();
        nut=item.getNutritionInfo();
        if(nut!=null){
            fiber=(float)nut.getFiber();
            protein=(float)nut.getProtein();
            calories=(float)nut.getCalories();
        }else{
            fiber=1;
            protein=1;
            calories=1;
        }
    }

    public Meal(String string){
     fromString(string);
    }

    public float getFiber() {
        return fiber;
    }

    public float getProtein() { return protein; }

    public float getCalories() { return calories; }

    public String getId() {
        return id;
    }
    public void setId(String s) { id=s; }

    public String getSource(){ return source;}
    public void setSource(String s){source=s;}

    public boolean equals(Meal meal) {

        if (calories == meal.calories && fiber == meal.fiber && protein == meal.protein && id == meal.id)
            return true;
        return false;
    }

    public String toString(){ return id+'/'+calories+'/'+fiber+'/'+protein;}
    public void fromString(String string){
        String[] data=string.split("/");
        id=data[0];
        calories=Float.valueOf(data[1]);
        fiber=Float.valueOf(data[2]);
        protein=Float.valueOf(data[3]);
    }

    public void setImageButton(ImageButton imageButton){ ib=imageButton;}
    public ImageButton getImageButton(){return ib;}
}
