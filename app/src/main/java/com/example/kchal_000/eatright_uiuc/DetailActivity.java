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
        Meal meal=new Meal(message);
        Point size=new Point();
        Display D=getWindowManager().getDefaultDisplay();
        final ArrayList<information.MenuItem> list=(ArrayList<information.MenuItem>)intent.getSerializableExtra("MealList");

        D.getSize(size);

        TextView name=new TextView(this);
        name.setText(meal.getId());
        name.setTextSize(20f);
        name.setPadding(0, 0, 0, 0);
        name.setTranslationX(0);
        name.setTranslationY(0);

        TextView cal=new TextView(this);
        cal.setText("Calories: "+meal.getCalories()+"(g)");
        cal.setTextSize(20f);
        cal.setPadding(0, 0, 0, 0);
        cal.setTranslationX(0);
        cal.setTranslationY(100);

        TextView fib=new TextView(this);
        fib.setText("Fiber: "+meal.getFiber()+"(g)");
        fib.setTextSize(20f);
        fib.setPadding(0, 0, 0, 0);
        fib.setTranslationX(0);
        fib.setTranslationY(200);

        TextView prot=new TextView(this);
        prot.setText("Protein: "+meal.getProtein()+"(g)");
        prot.setTextSize(20f);
        prot.setPadding(0, 0, 0, 0);
        prot.setTranslationX(0);
        prot.setTranslationY(300);

        Button back=new Button(this);
        back.setText("Back");
        back.setTranslationY(400);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("MealList",list);
                startActivity(intent);
            }
        });

        addContentView(back,new ViewGroup.LayoutParams(size.x,200));
        addContentView(name, new ViewGroup.LayoutParams(size.x, 100));
        addContentView(cal, new ViewGroup.LayoutParams(600, 100));
        addContentView(fib, new ViewGroup.LayoutParams(600, 100));
        addContentView(prot, new ViewGroup.LayoutParams(600, 100));

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
