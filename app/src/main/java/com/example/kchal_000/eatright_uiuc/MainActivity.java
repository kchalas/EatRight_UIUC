package com.example.kchal_000.eatright_uiuc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        //decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Meal[] pointList;
        final MealCombination mainCombination=new MealCombination();
        final SwipeCombination swipeCombination;
        int pointNumber=5;
        Point size=new Point();
        float unitx, unity;
        float maxProtein=64f;
        float maxFiber=29f;
        Display D=getWindowManager().getDefaultDisplay();

        pointList=new Meal[pointNumber];

        D.getSize(size);
        unity=(float)(size.x/maxProtein);
        unitx=(float)((size.y-250)/maxFiber);

        //pointList[0]=new Meal(7,80,500,"Min Target");
        //pointList[1]=new Meal(14,160,500,"Max Target");
        pointList[2]=new Meal(1,56,830,"Baconator");
        pointList[3]=new Meal(0,86,379,"Steak");
        pointList[4]=new Meal(3,0.5f,91,"Apple(1)");
        pointList[0]=new Meal(4,1f,71,"Orange(1)");
        pointList[1]=new Meal(3,26,520,"Big Mac");

        swipeCombination=new SwipeCombination(pointList);

        makeAxisLabels(this,maxFiber,maxProtein,size);

        for(int i=0; i<pointNumber;i++) {
            addContentView(toImageButton(pointList[i],this,unitx,unity,mainCombination,swipeCombination), new ViewGroup.LayoutParams(50, 50));
        }

        ImageButton comb=new ImageButton(this);
        comb.setBackgroundResource(R.drawable.point);
        comb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("Meal",mainCombination.toString());
                startActivity(intent);
            }
        });


        mainCombination.setImageButton(comb,unitx, unity);
        addContentView(mainCombination.getImageButton(), new ViewGroup.LayoutParams(75, 75));
        userInterface(this,size,mainCombination,swipeCombination);
    }

    public void makeAxisLabels(Context context, float maxFiber, float maxProtein, Point size){
        TextView xmax, ymax, origin;
        View redZone,yellowZone,greenZone, target;


        origin=new TextView(this);
        origin.setClickable(false);
        origin.setText("0");
        origin.setTextSize(12f);
        origin.setPadding(0,0,0,0);
        origin.setTranslationX(0);
        origin.setTranslationY(0);

        xmax=new TextView(this);
        xmax.setText(""+maxProtein);
        xmax.setTextSize(12f);
        xmax.setPadding(0,0,0,0);
        xmax.setTranslationX(size.x-60);
        xmax.setTranslationY(0);

        ymax=new TextView(this);
        ymax.setText("" + maxFiber);
        ymax.setTextSize(12f);
        ymax.setPadding(0,0,0,0);
        ymax.setTranslationX(0);
        ymax.setTranslationY(size.y-300);

        redZone=new View(this);
        redZone.setBackgroundResource(R.drawable.red);
        redZone.setPadding(0,0,0,0);
        redZone.setTranslationX(0);
        redZone.setTranslationY(0);

        yellowZone=new View(this);
        yellowZone.setBackgroundResource(R.drawable.yellow);
        yellowZone.setPadding(0,0,0,0);
        yellowZone.setTranslationX(0);
        yellowZone.setTranslationY(0);

        greenZone=new View(this);
        greenZone.setBackgroundResource(R.drawable.green);
        greenZone.setPadding(0,0,0,0);
        greenZone.setTranslationX(0);
        greenZone.setTranslationY(0);

        target=new View(this);
        target.setBackgroundResource(R.drawable.target);
        target.setPadding(0,0,0,0);
        target.setTranslationX((size.x)/4);
        target.setTranslationY((size.y-300)/4);

        addContentView(greenZone,new ViewGroup.LayoutParams((size.x),(size.y-200)));
        addContentView(yellowZone,new ViewGroup.LayoutParams((size.x)/2,(size.y-300)/2));
        addContentView(redZone,new ViewGroup.LayoutParams((size.x)/4,(size.y-300)/4));
        addContentView(target,new ViewGroup.LayoutParams((size.x)/4,(size.y-300)/4));

        addContentView(origin, new ViewGroup.LayoutParams(50, 50));
        addContentView(xmax,new ViewGroup.LayoutParams(50,50));
        addContentView(ymax,new ViewGroup.LayoutParams(50,50));
    }

    public void userInterface(Context context, Point size,final MealCombination mealCombination, final SwipeCombination swipeCombination){
        ImageButton add, combine, swipe;

        add=new ImageButton(this);
        add.setBackgroundResource(R.drawable.add);
        add.setTranslationX(size.x-150);
        add.setTranslationY(size.y - 400);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RestaurantChoices.class);
                startActivity(intent);
            }
        });

        combine=new ImageButton(this);
        combine.setBackgroundResource(R.drawable.combine);
        combine.setTranslationX(size.x-310);
        combine.setTranslationY(size.y - 400);
        combine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(mealCombination.getCombine()){
                    mealCombination.unsetCombine();
                }else{
                    mealCombination.setCombine();
                    if(swipeCombination.getSwiping()){
                        swipeCombination.unsetSwiping();
                        swipeCombination.finalize();
                        mealCombination.update();
                    }
                }
            }
        });
        mealCombination.setIcon(combine);

        swipe=new ImageButton(this);
        swipe.setBackgroundResource(R.drawable.swipe);
        swipe.setTranslationX(size.x-470);
        swipe.setTranslationY(size.y - 400);
        swipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(swipeCombination.getSwiping()){
                    swipeCombination.unsetSwiping();
                    swipeCombination.finalize();
                    mealCombination.update();
                }else{
                    swipeCombination.setSwiping();
                    if(mealCombination.getCombine()){
                        mealCombination.unsetCombine();
                    }
                }
            }
        });
        swipeCombination.setIcon(swipe);

        addContentView(add,new ViewGroup.LayoutParams(150,150));
        addContentView(combine,new ViewGroup.LayoutParams(150,150));
        addContentView(swipe,new ViewGroup.LayoutParams(150,150));

    }

    public ImageButton toImageButton(final Meal meal, Context context,float ux, float uy,final MealCombination mealCombination, final SwipeCombination swipeCombination){
        ImageButton ib=new ImageButton(context);
        float posx=0,posy=0;

        ib.setBackgroundResource(R.drawable.point);
        posx=(meal.getProtein() / (meal.getCalories())) * uy * 100;
        posy=(meal.getFiber() / (meal.getCalories()/500)) * ux;
        ib.setTranslationX(posx-25);
        ib.setTranslationY(posy-25);
        ib.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(mealCombination.getCombine() ){
                    mealCombination.addDropMeal(meal);
                    if (mealCombination.isCombined(meal)) {
                        v.setBackgroundResource(R.drawable.pointcombination);
                    } else {
                        v.setBackgroundResource(R.drawable.point);
                    }
                } else {
                    if(swipeCombination.getSwiping()){
                        swipeCombination.addDrop(v);
                        if(mealCombination.isCombined(meal)){
                            mealCombination.addDropMeal(meal);
                        }
                        if (swipeCombination.isSwiped(meal)) {
                            v.setBackgroundResource(R.drawable.pointswiping);
                        } else {
                            v.setBackgroundResource(R.drawable.point);
                        }
                    } else {
                        Intent intent = new Intent(v.getContext(), DetailActivity.class);
                        intent.putExtra("Meal",meal.toString());

                        startActivity(intent);
                    }
                }
            }
        });
        meal.setImageButton(ib);

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
