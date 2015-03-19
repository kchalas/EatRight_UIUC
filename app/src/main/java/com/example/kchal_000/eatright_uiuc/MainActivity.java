package com.example.kchal_000.eatright_uiuc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Meal[] pointList;
        MealCombination mainCombination=new MealCombination();
        int pointNumber=3;
        Point size=new Point();
        float unitx, unity;
        float maxProtein=0.3f;
        float maxFiber=0.2f;
        Display D=getWindowManager().getDefaultDisplay();

        pointList=new Meal[pointNumber];

        D.getSize(size);
        unity=(float)(size.x/maxProtein);
        unitx=(float)((size.y-250)/maxFiber);

        //pointList[0]=new Meal(0,29,100);
        //pointList[1]=new Meal(27,0,100);
        pointList[2]=new Meal(1,56,830,"Baconator");
        pointList[0]=new Meal(0,86,379,"Steak");
        pointList[1]=new Meal(16,7,160,"Celery(1kg)");

        for(int i=0; i<pointNumber;i++) {
            addContentView(toImageButton(pointList[i],this, unitx, unity,mainCombination), new ViewGroup.LayoutParams(50, 50));
        }

        ImageButton comb=new ImageButton(this);
        comb.setBackgroundResource(R.drawable.point);
        comb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);

                startActivity(intent);
            }
        });


        mainCombination.setImageButton(comb,unitx, unity);
        addContentView(mainCombination.getImageButton(), new ViewGroup.LayoutParams(75, 75));
        makeAxisLabels(this,maxFiber,maxProtein,size);
        userInterface(this,size,mainCombination);
    }

    public void makeAxisLabels(Context context, float maxFiber, float maxProtein, Point size){
        EditText xmax, ymax, origin;

        origin=new EditText(this);
        origin.setText("0");
        origin.setTextSize(12f);
        origin.setPadding(0,0,0,0);
        origin.setTranslationX(0);
        origin.setTranslationY(0);

        xmax=new EditText(this);
        xmax.setText(""+maxProtein);
        xmax.setTextSize(12f);
        xmax.setPadding(0,0,0,0);
        xmax.setTranslationX(size.x-60);
        xmax.setTranslationY(0);

        ymax=new EditText(this);
        ymax.setText("" + maxFiber);
        ymax.setTextSize(12f);
        ymax.setPadding(0,0,0,0);
        ymax.setTranslationX(0);
        ymax.setTranslationY(size.y - 300);

        addContentView(origin,new ViewGroup.LayoutParams(50,50));
        addContentView(xmax,new ViewGroup.LayoutParams(50,50));
        addContentView(ymax,new ViewGroup.LayoutParams(50,50));
    }

    public void userInterface(Context context, Point size,final MealCombination mealCombination){
        ImageButton add, combine;

        add=new ImageButton(this);
        add.setBackgroundResource(R.drawable.square);
        add.setTranslationX(size.x-150);
        add.setTranslationY(size.y - 400);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RestaurantChoices.class);
                startActivity(intent);
            }
        });

        combine=new ImageButton(this);
        combine.setBackgroundResource(R.drawable.square);
        combine.setTranslationX(size.x-310);
        combine.setTranslationY(size.y - 400);
        combine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(mealCombination.getCombine()){
                    mealCombination.unsetCombine();
                }else{
                    mealCombination.setCombine();
                }
            }
        });



        addContentView(add,new ViewGroup.LayoutParams(150,150));
        addContentView(combine,new ViewGroup.LayoutParams(150,150));


    }

    public ImageButton toImageButton(final Meal meal, Context context,float ux, float uy,final MealCombination mealCombination){
        ImageButton ib=new ImageButton(context);

        ib.setBackgroundResource(R.drawable.point);
        ib.setTranslationX((meal.getProtein() / meal.getCalories()) * uy);
        ib.setTranslationY((meal.getFiber() / meal.getCalories()) * ux);
        ib.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!mealCombination.getCombine()){
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("Meal.ID",meal.getId());

                startActivity(intent);}
                else{
                    mealCombination.addMeal(meal);
                }
            }
        });

        return ib;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
