package com.example.kchal_000.eatright_uiuc;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;

import java.util.ArrayList;

import api.apiInterface;
import information.RestaurantInfo;


public class RestaurantChoices extends ActionBarActivity {

    private static final int[] button_ids = {
            R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8,
            R.id.button9, R.id.button10, R.id.button11, R.id.button12,
            R.id.button13, R.id.button14, R.id.button15, R.id.button16,
            R.id.button17, R.id.button18
    };
    private ArrayList<RestaurantInfo> rests;

    /**private static final String[] rests = {
            "McDonalds", "Subway", "Taco Bell", "Chipotle",
            "Jay Gumbos", "Dunkin Donuts", "Starbucks", "Pizza Hut",
    };*/
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant__choices);
        Intent intent = getIntent();
        String fName = intent.getStringExtra("name");
        //double[] location = getLocation();

        rests = apiInterface.getRestaurants(40.11000, -88.22700);

        Button[] buttons = {
                (Button) findViewById(button_ids[0]), (Button) findViewById(button_ids[1]),
                (Button) findViewById(button_ids[2]), (Button) findViewById(button_ids[3]),
                (Button) findViewById(button_ids[4]), (Button) findViewById(button_ids[5]),
                (Button) findViewById(button_ids[6]), (Button) findViewById(button_ids[7]),
                (Button) findViewById(button_ids[8]), (Button) findViewById(button_ids[9]),
                (Button) findViewById(button_ids[10]), (Button) findViewById(button_ids[11]),
                (Button) findViewById(button_ids[12]), (Button) findViewById(button_ids[13]),
                (Button) findViewById(button_ids[14]), (Button) findViewById(button_ids[15]),
                (Button) findViewById(button_ids[16]), (Button) findViewById(button_ids[17]),
        };


        for(int i = 0; i < rests.size(); i++) {
            buttons[i].setText(rests.get(i).getName());
            buttons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent myIntent = new Intent(view.getContext(), MenuOfRestaurant.class);

                    startActivityForResult(myIntent, 0);
                }

            });

        }
        Button refresh = (Button) findViewById(R.id.buttonRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), RestaurantChoices.class);
                startActivityForResult(myIntent, 0);
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant__choices, menu);
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

    public double[] getLocation() {
        /**
         * Location code from
         * http://stackoverflow.com
         * /questions/2227292/how-to-get-latitude-and-longitude-
         * of-the-mobiledevice-in-android
         */
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        double[] loc = {latitude, longitude};
        return loc;
    }

}
