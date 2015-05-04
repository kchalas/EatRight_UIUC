package com.example.kchal_000.eatright_uiuc;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String message = intent.getStringExtra("Meal");
        final String combinationMessage= intent.getStringExtra("MealCombination");
        final Meal meal;
        final MealCombination mealCombination=new MealCombination();
        final ArrayList<information.MenuItem> combinationList=(ArrayList<information.MenuItem>)intent.getSerializableExtra("CombinationList");
        if(message==null) {                                 //if meal isn't passed
            mealCombination.fromString(combinationMessage); //initializes combination,
            mealCombination.setMealList(combinationList);   //its internal meal list
            meal=new Meal(combinationMessage);              //and creates a meal with its information
        }else{                                              //if meal is passed
            meal = new Meal(message);                       //creates meal
        }
        Point size=new Point();
        Display D=getWindowManager().getDefaultDisplay();
        final ArrayList<information.MenuItem> list=(ArrayList<information.MenuItem>)intent.getSerializableExtra("MealList"); //creates global list to be passed on

        D.getSize(size);

        int sizey=75;                                           //changes size depending on name length
        if(meal.getId().length()*25>size.x){
            sizey=75*(((meal.getId().length()*25)/size.x)+1);
        }
        int posy=sizey;                                         //carries down the size change

        TextView name=new TextView(this);
        name.setText(meal.getId());                          //shows name
        name.setTextSize(20f);
        name.setPadding(0, 0, 0, 0);
        name.setTranslationX(0);
        name.setTranslationY(0);

        TextView cal=new TextView(this);
        cal.setText("Calories: "+meal.getCalories()+"(g)"); //calories
        cal.setTextSize(20f);
        cal.setPadding(0, 0, 0, 0);
        cal.setTranslationX(0);
        cal.setTranslationY(posy);  posy+=100;

        TextView fib=new TextView(this);
        fib.setText("Fiber: "+meal.getFiber()+"(g)");       //fiber
        fib.setTextSize(20f);
        fib.setPadding(0, 0, 0, 0);
        fib.setTranslationX(0);
        fib.setTranslationY(posy);  posy+=100;

        TextView prot=new TextView(this);
        prot.setText("Protein: "+meal.getProtein()+"(g)");  //and protein
        prot.setTextSize(20f);
        prot.setPadding(0, 0, 0, 0);
        prot.setTranslationX(0);
        prot.setTranslationY(posy); posy+=100;

        Button back=new Button(this);
        back.setText("Back");
        back.setTranslationY(posy);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                if(combinationMessage!=null){                                       //is combination was passed, pass it back
                    intent.putExtra("MealCombination",mealCombination.toString());
                    intent.putExtra("CombinationList",combinationList);
                }
                intent.putExtra("MealList",list);                                   //return global list
                startActivity(intent);
            }
        });

        addContentView(name, new ViewGroup.LayoutParams(size.x, sizey));
        addContentView(cal, new ViewGroup.LayoutParams(600, 100));
        addContentView(fib, new ViewGroup.LayoutParams(600, 100));
        addContentView(prot, new ViewGroup.LayoutParams(600, 100));
        addContentView(back,new ViewGroup.LayoutParams(size.x,200));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
