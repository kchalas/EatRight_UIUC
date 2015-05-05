package com.example.kchal_000.eatright_uiuc;

import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by Francisco RC on 4/12/2015.
 */
public class SwipeCombination {

    ArrayList<View> viewList= new ArrayList<View>();
    ArrayList<Meal> mealList= new ArrayList<Meal>();;
    boolean swiping=false;
    ImageButton ib;

    public SwipeCombination(ArrayList<Meal> meallist){
        mealList=meallist;
    }

    public void setIcon(ImageButton imageButton){
        ib=imageButton;
    }

    public void addDrop(View ib){
        if(viewList.contains(ib)){
            viewList.remove(ib);
        }else{
            viewList.add(ib);
        }
    }

    public void finalizeSwipe(){
        boolean flag=true;
        ArrayList<ImageButton> newList=new ArrayList<ImageButton>();
        int counter=0;

        if(viewList.size()>0) {                                             //if there is any selected point to swipe

            ArrayList<Meal> newMealList=new ArrayList<Meal>();

            for (Meal meal : mealList) {
                if(!viewList.contains(meal.getImageButton())){              //if it isn't selected for swiping
                    newMealList.add(meal);                                  //add it to the new global meal list
                }else{                                                      //if selected for swiping
                    meal.getImageButton().setClickable(false);              //render it unclickable
                    meal.getImageButton().setVisibility(View.INVISIBLE);    //and invisible both outside
                    meal.getImageView().setVisibility(View.INVISIBLE);      //and inside
                }
            }
            mealList = newMealList;                                         //update the meal list
        }
    }

    public boolean isSwiped(Meal meal){
        if(viewList.contains(meal.getImageButton())){       //if meal selected fro swiping
            return true;                                    //return true
        }
        return false;                                       //if not, false
    }

    public void setSwiping(){
        swiping=true;
        ib.setBackgroundResource(R.drawable.swipetoggle);
    }
    public void unsetSwiping(){
        swiping=false;
        ib.setBackgroundResource(R.drawable.swipe);
    }
    public boolean getSwiping(){return  swiping;}

    public ArrayList<Meal> getList(){return mealList;}
}
