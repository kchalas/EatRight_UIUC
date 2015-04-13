package com.example.kchal_000.eatright_uiuc;

import android.view.View;
import android.widget.ImageButton;

import com.example.kchal_000.eatright_uiuc.Meal;

import java.util.ArrayList;

/**
 * Created by Francisco RC on 4/12/2015.
 */
public class SwipeCombination {

    ArrayList<View> viewList= new ArrayList<View>();
    Meal[] mealList;
    boolean swiping=false;
    ImageButton ib;

    public SwipeCombination(Meal[] meallist){
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

    public void finalize(){
        boolean flag=true;
        boolean[] newList=new boolean[mealList.length];
        int counter=0;

        if(viewList.size()>0) {

            for (int i = 0; i < newList.length; i++) {
                newList[i] = true;
            }

            for (View ib : viewList) {
                for (int i = 0; i < mealList.length; i++) {
                    if (ib == mealList[i].getImageButton()) {
                        newList[i] = false;
                        counter += 1;
                        break;
                    }
                }
                ib.setClickable(false);
                ib.setVisibility(View.INVISIBLE);
            }

            Meal[] newMealList = new Meal[mealList.length - counter];
            counter=0;

            for (int i = 0; i < mealList.length; i++) {
                if (newList[i]) {
                    newMealList[counter] = mealList[i];
                    counter += 1;
                }
            }

            mealList = newMealList;
        }
    }

    public boolean isSwiped(Meal meal){
        if(viewList.contains(meal.getImageButton())){
            return true;
        }
        return false;
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
}
