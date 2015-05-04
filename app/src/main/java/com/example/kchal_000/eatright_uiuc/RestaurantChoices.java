package com.example.kchal_000.eatright_uiuc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import api.apiInterface;
import information.RestaurantInfo;


public class RestaurantChoices extends ActionBarActivity {
    //hashmap of restaurants
    private HashMap<String, RestaurantInfo> rests = new HashMap<String, RestaurantInfo>();

    /**
     * Dynamic buttons and dynamic view, both populated
     * with Yelp API info based on geographic location of the
     * user.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dynamic view
        ScrollView sv = new ScrollView(this);
        sv.setBackgroundColor(Color.parseColor("#BED661"));
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);
        //create an array of buttons
        ArrayList<Button> buttons = new ArrayList<Button>();
        //cool color pallette to reduce eating
        String[] otherPallette = {"#78D5E3", "#7AF5F5", "#34DDDD", "#93E2D5"};
        String[] array = {"#6CCECB", "#F9E559", "#EF7126" ,"#8EDC9D"};
        double[] location = getLocation(); //lat , long
        ArrayList<RestaurantInfo> temp = apiInterface.getRestaurants(location[0], location[1]);
        //pass along data if anything in visualization
        Intent restIntent = getIntent();
        final ArrayList<information.MenuItem> chosenItems = (ArrayList<information.MenuItem>)restIntent.getSerializableExtra("MealList");
        //populate list with all found restaurants.
        if(temp!=null ) {
            if(!temp.isEmpty()) {
                for (RestaurantInfo e : temp) {
                    rests.put(e.getName(), e);
                }
            }
        }

        if(rests != null) {
            if(!rests.isEmpty()) {
                //create layout and dynamic buttons
                for (int i = 0; i < rests.size(); i++) {
                    final Button button = new Button(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(20, 10, 20, 10);
                    button.setLayoutParams(params);
                    button.setBackgroundColor(Color.parseColor(otherPallette[i % otherPallette.length]));
                    button.setText(temp.get(i).getName());
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent myIntent = new Intent(view.getContext(), MenuOfRestaurant.class);
                            myIntent.putExtra("name", button.getText());
                            myIntent.putExtra("rest", rests.get(button.getText()));
                            myIntent.putExtra("MealList", chosenItems);
                            startActivityForResult(myIntent, 0);
                        }

                    });
                    buttons.add(button);
                    ll.addView(button);
                }
            }else{
                Context context = getApplicationContext();
                CharSequence txt = "No Restaurants Nearby!";
                Toast tst = Toast.makeText(context, txt, Toast.LENGTH_LONG);
                tst.show();
            }
        }else{
            //inform user that no restaurants are available.
            Context context = getApplicationContext();
            CharSequence txt = "No Restaurants Nearby!";
            Toast tst = Toast.makeText(context, txt, Toast.LENGTH_LONG);
            tst.show();
        }

        Button refresh = new Button(this);
        refresh.setText("Locate Restaurants");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        refresh.setLayoutParams(params);
        refresh.setPadding(40, 40, 40, 40);
        refresh.setBackgroundColor(Color.parseColor("#89E894"));
        ll.addView(refresh);
        //move to the next screen to get the menus
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), RestaurantChoices.class);
                startActivityForResult(myIntent, 0);
            }

        });
        this.setContentView(sv);

    }

    /**
     * Location code from
     * http://stackoverflow.com
     * /questions/2227292/how-to-get-latitude-and-longitude-of-the-mobiledevice-in-android
     * Just in case a location isn't found, provide that of Champaign Urbana
     * @return
     */
    public double[] getLocation() {
        double longitude;
        double latitude;
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location==null){
            longitude = -88.22700;
            latitude = 40.11000;
        }else{
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        double[] loc = {latitude, longitude};
        return loc;
    }

}
