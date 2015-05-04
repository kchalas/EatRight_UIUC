package com.example.kchal_000.eatright_uiuc;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageButton;
import android.view.View;
import android.widget.ImageView;

import information.MenuItem;
import information.NutritionInfo;

/**
 * Created by Francisco RC on 2/20/2015.
 */
public class Meal {

    float fiber = 0;          //x position of the point
    float protein = 0;        //y position of the point
    float calories = 0;       //base for position and size
    int size=0;
    String id = "";           //name
    String source="";         //name of restaurant
    ImageButton ib;
    ImageView iv;

    public Meal(float f, float p, float c, String s) {
        protein = p;
        fiber = f;
        calories = c;
        id = s;
        sizeFromCal();
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
        sizeFromCal();
    }

    public Meal(String string){
        fromString(string);
        sizeFromCal();
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

    public void setImageView(ImageView imageView){ iv=imageView;}
    public ImageView getImageView(){return iv;}

    void sizeFromCal(){
        int change= (int) (0.08*(calories%250));
        switch((int)calories/250){
            case 0: size=40+change; break; //low cal
            case 1: size=60+change; break; //med cal
            case 2: size=80+change; break; //high cal
            default: size=110; break;//very high cal
        }
    }                         //sets size depending on total calories(0.08=20/250)(20 being the max change and 250 max calories )
    public int getSize(){return size;}
    public boolean equals(MenuItem menuItem){
        NutritionInfo ni=menuItem.getNutritionInfo();
        if(id.equals(ni.getName()) && protein==(float)ni.getProtein() && fiber==(float)ni.getFiber() && calories==(float)ni.getCalories()){
            return true;
        }
        return false;
    }   //helps restoring the combination
}
