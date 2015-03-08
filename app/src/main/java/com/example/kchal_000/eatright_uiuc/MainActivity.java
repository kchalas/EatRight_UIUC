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
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Meal[] pointList;
        int pointNumber=2;
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
        //pointList[2]=new Meal(0,0,100);
        pointList[0]=new Meal(0,86,379,"Steak");
        pointList[1]=new Meal(16,7,160,"Celery(1kg)");

        for(int i=0; i<pointNumber;i++) {
            addContentView(toImageButton(pointList[i],this, unitx, unity), new ViewGroup.LayoutParams(50, 50));
        }

        makeAxisLabels(this,maxFiber,maxProtein,size);
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

    public ImageButton toImageButton(Meal meal, Context context,float ux, float uy){
        ImageButton ib=new ImageButton(context);

        ib.setBackgroundResource(R.drawable.point);
        ib.setTranslationX((meal.getProtein()/meal.getCalories())*uy);
        ib.setTranslationY((meal.getFiber()/meal.getCalories())*ux);
        ib.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                String name =getString(R.string.Aux);
                intent.putExtra("Meal.ID",name);

                startActivity(intent);
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
