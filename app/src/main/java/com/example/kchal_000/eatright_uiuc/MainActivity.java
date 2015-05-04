package com.example.kchal_000.eatright_uiuc;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import information.NutritionInfo;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //stops the screen from rotating

        ArrayList<Meal> pointList =new ArrayList<Meal>();
        final MealCombination mainCombination=new MealCombination();
        final SwipeCombination swipeCombination;
        Point size=new Point();
        float unitx, unity;
        float maxProtein=64f;                                  //max protein set by client
        float maxFiber=29f;                                    //max fiber set by client
        Intent intent=getIntent();
        Display D=getWindowManager().getDefaultDisplay();
        ArrayList<information.MenuItem> intentList=(ArrayList<information.MenuItem>)intent.getSerializableExtra("MealList");//receives global list(if sent)
        String combinationMessage= intent.getStringExtra("MealCombination");                                                //and combination(if sent)

        D.getSize(size);
        unity=(float)(size.x/maxProtein);                       //unit y vector
        unitx=(float)((size.y-250)/maxFiber);                   //unit x vector

        if(intentList!=null && intentList.size()>0){
            for(information.MenuItem item: intentList){
                pointList.add(new Meal(item));                  //if a list was sent, rebuild it
            }
        }

        swipeCombination=new SwipeCombination(pointList);
        makeAxisLabels(this,maxFiber,maxProtein,size);

        for(Meal meal: pointList) {                         //draw the outsides and insides of the points
            addContentView(toImageButton(meal,this,unitx,unity,mainCombination,swipeCombination, size), new ViewGroup.LayoutParams(meal.getSize(), meal.getSize()));
            addContentView(toImageView(meal,this,unitx,unity,size),new ViewGroup.LayoutParams(meal.getSize(),meal.getSize()));
        }
        if(combinationMessage!=null){                       //if a combination is sent, rebuild it
            mainCombination.fromString(combinationMessage);
            mainCombination.setFlawlessMealList((ArrayList<information.MenuItem>)intent.getSerializableExtra("CombinationList"),pointList);
        }

        ImageButton comb=new ImageButton(this);
        comb.setBackgroundResource(R.drawable.point);
        comb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<information.MenuItem> list=new ArrayList<information.MenuItem>();
                for(Meal meal: swipeCombination.getList()){                     //creates the global list(possible refactoring)
                    list.add(new information.MenuItem(meal));
                }
                ArrayList<information.MenuItem> clist=new ArrayList<information.MenuItem>();
                for(Meal meal: mainCombination.getMealList()){                  //and the combination list(possible refactoring)
                    clist.add(new information.MenuItem(meal));
                }
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("MealCombination",mainCombination.toString());  //passes the combinaton
                intent.putExtra("CombinationList",clist);                       // the combination list
                intent.putExtra("MealList",list);                               // and the global list
                startActivity(intent);
            }
        });


        mainCombination.setImageButton(comb,unitx, unity);                      //sets the image and the unit vectors for future updates
        addContentView(mainCombination.getImageButton(), new ViewGroup.LayoutParams(mainCombination.getSize(), mainCombination.getSize()));
        mainCombination.update();
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
        add.setTranslationX(size.x-150);          //correcting for its own size
        add.setTranslationY(size.y - 400);        //correcting for lower bar
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<information.MenuItem> list=new ArrayList<information.MenuItem>();
                ArrayList<Meal> pList=swipeCombination.getList();
                for(Meal meal: pList){
                    list.add(new information.MenuItem(meal));   //creating updated global list
                }
                Intent intent = new Intent(v.getContext(), RestaurantChoices.class);
                intent.putExtra("MealList",list); //passing global list
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
                    mealCombination.unsetCombine();         //unsets combining
                }else{
                    mealCombination.setCombine();
                    if(swipeCombination.getSwiping()){
                        swipeCombination.unsetSwiping();    //if swiping unsets swiping, finalizes swiping, sets combining and updates the combination
                        swipeCombination.finalizeSwipe();
                        mealCombination.update();
                    }
                }
            }
        });
        mealCombination.setIcon(combine);                   //allows to change the icon remotely(without clicking on that icon)

        swipe=new ImageButton(this);
        swipe.setBackgroundResource(R.drawable.swipe);
        swipe.setTranslationX(size.x-470);
        swipe.setTranslationY(size.y - 400);
        swipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(swipeCombination.getSwiping()){
                    swipeCombination.unsetSwiping();        //unsets swiping, finalizes the swipe and updates the combination
                    swipeCombination.finalizeSwipe();
                    mealCombination.update();
                }else{
                    swipeCombination.setSwiping();
                    if(mealCombination.getCombine()){
                        mealCombination.unsetCombine();     //sets swiping and unsets combination if combination is active
                    }
                }
            }
        });
        swipeCombination.setIcon(swipe);                    //allows to change the icon remotely(without clicking on that icon)

        addContentView(add,new ViewGroup.LayoutParams(150,150));
        addContentView(combine,new ViewGroup.LayoutParams(150,150));
        addContentView(swipe,new ViewGroup.LayoutParams(150,150));

    }

    public ImageButton toImageButton(final Meal meal, Context context,float ux, float uy,final MealCombination mealCombination, final SwipeCombination swipeCombination, Point size){
        ImageButton ib=new ImageButton(context);
        float posx,posy;

        ib.setBackgroundResource(R.drawable.pointout);
        posx=(meal.getProtein() / (meal.getCalories())) * uy * 100;
        if(posx>size.x){posx=size.x;}                                //stops points from appearing outside of the screen
        posy=(meal.getFiber() / (meal.getCalories()/500)) * ux;
        if(posy>size.y){posy=size.y;}                                //stops points from appearing outside of the screen
        ib.setTranslationX(posx-(meal.getSize()/2));                 //makes the center of the image the exact cordinates(instead of the upper left corner)
        ib.setTranslationY(posy-(meal.getSize()/2));                 //makes the center of the image the exact cordinates(instead of the upper left corner)
        ib.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(mealCombination.getCombine() ){
                    mealCombination.addDropMeal(meal);                      //adds/removes the point to the combination if combining is on
                    if (mealCombination.isCombined(meal)) {
                        v.setBackgroundResource(R.drawable.pointoutcomb);   //and changes the image
                    } else {
                        v.setBackgroundResource(R.drawable.pointout);
                    }
                } else {
                    if(swipeCombination.getSwiping()){
                        swipeCombination.addDrop(v);                        //adds/removes the point for swiping if swiping is on
                        if(mealCombination.isCombined(meal)){
                            mealCombination.addDropMeal(meal);              //if a point is both combining and swiping we remove it from the combination
                        }
                        if (swipeCombination.isSwiped(meal)) {
                            v.setBackgroundResource(R.drawable.pointoutswipe);//and changes the image
                        } else {
                            v.setBackgroundResource(R.drawable.pointout);
                        }
                    } else {
                        ArrayList<information.MenuItem> list=new ArrayList<information.MenuItem>(); //if neither swiping nor combining we move to the detail window
                        for(Meal meal: swipeCombination.getList()){
                            list.add(new information.MenuItem(meal));            //create a list, of MenuItems(serializable) to pass on, from the swiping updated list
                        }
                        Intent intent = new Intent(v.getContext(), DetailActivity.class);
                        intent.putExtra("Meal",meal.toString());                 //passing the selected Meal
                        intent.putExtra("MealList",list);                        //and global Meal list
                        startActivity(intent);
                    }
                }
            }
        });
        meal.setImageButton(ib);                                                //saves the ImageView to render it invisible and unclickable when deleting

        return ib;
    }

    public ImageView toImageView(Meal meal, Context context, float ux, float uy, Point size){
        ImageView iv=new ImageView(context);
        float posx,posy;

        iv.setBackgroundResource(drawFromCal(meal.getCalories()));
        posx=(meal.getProtein() / (meal.getCalories())) * uy * 100;
        if(posx>size.x){posx=size.x;}                               //stops points from appearing outside of the screen
        posy=(meal.getFiber() / (meal.getCalories()/500)) * ux;
        if(posy>size.y){posy=size.y;}                               //stops points from appearing outside of the screen
        iv.setTranslationX(posx-(meal.getSize()/2));
        iv.setTranslationY(posy-(meal.getSize()/2));
        meal.setImageView(iv);                                      //saves the ImageView to render it invisible when deleting

        return iv;
    } //draws the interior of the points

    public int drawFromCal(float calories){
        switch((int)calories/250){
            case 0: return R.drawable.pointinlow; //low cal
            case 1: return R.drawable.pointinmed; //med cal
            case 2: return R.drawable.pointinhigh; //high cal
            default: return R.drawable.pointinhigh;//very high cal
        }
    } // returns a Drawable ID depending on the calories

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
