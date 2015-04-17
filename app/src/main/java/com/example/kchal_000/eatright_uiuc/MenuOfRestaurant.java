package com.example.kchal_000.eatright_uiuc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import api.MenuProvider;
import api.apiInterface;
import information.CalorieCategory;
import information.RestaurantInfo;
import information.MenuItem;

import static android.widget.CompoundButton.OnCheckedChangeListener;

/*
   Helped along with details from :
   http://androidcodesnips.blogspot.com/2011/09/three-level-expandable-list.html
 */

public class MenuOfRestaurant extends ActionBarActivity implements
        OnCheckedChangeListener{
    ExpandableListView expt;
    private ArrayList<MenuItem> chosenItems = new ArrayList<MenuItem>();
    private HashMap<CalorieCategory, HashMap<information.MenuItem, List<String>>> allData;
    private MenuProvider mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent restIntent = getIntent();
        RestaurantInfo rInfo = (RestaurantInfo)restIntent.getSerializableExtra("rest");
        //need to use RestaurantInfo to create a data struct to give to contentView
        mp = apiInterface.getMenu(rInfo);
        //Log.i("json to parse ", apiInterface.getMenu(rInfo).toString());
        //set up content view
        this.allData = mp.getMenu();
        //this.allData = DataProvider.getData();
        setContentView(R.layout.activity_menu_of_restaurant);
        expt = (ExpandableListView) findViewById(R.id.expandableListView);
        expt.setAdapter(new FirstLevelAdapter(this, this.allData));


        Button back = (Button) findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), RestaurantChoices.class);
                startActivityForResult(myIntent, 0);
            }

        });

        Button pushToViz = (Button) findViewById(R.id.buttonToViz);
        pushToViz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                chosenItems = mp.getCheckedItems();


                Log.i("chosenItemsTopass ", ""+chosenItems.size());
                //Log.i("chosenItems ", ""+chosenItems.get(0));
                myIntent.putExtra("choices", chosenItems);
                startActivityForResult(myIntent, 0);
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_of_restaurant, menu);
        return true;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Context ctxt = getApplicationContext();

        //expt.getSelectedItem()
        //int pos = expt.getPositionForView(buttonView);
        if(buttonView.getTag() instanceof CalorieCategory){
            CalorieCategory key = (CalorieCategory) buttonView.getTag();
            key.setSelected(!key.isSelected()); //toggle
            ArrayList<MenuItem> items = mp.getCategory(key);   // <---------------uncomment
            HashMap<MenuItem, List<String>> test = allData.get(key);
            //Set<MenuItem> items = test.keySet();   testing alternative to items ArrayList

            CharSequence txt = "Nearby!"+key.getName();
            Toast tst = Toast.makeText(ctxt, txt, Toast.LENGTH_LONG);
            tst.show();
            if(key.isSelected()) {
                for (MenuItem i : items) {
                    i.setSelected(true);
                }
            }else{
                for(MenuItem i : items){
                    i.setSelected(false);
                }
            }

        }else if(buttonView.getTag() instanceof information.MenuItem){
            MenuItem key = (information.MenuItem) buttonView.getTag();
            HashMap<MenuItem, List<String>> categItems = allData.get(key);
            key.setSelected(!key.isSelected()); //toggle
            if(key.isSelected()){
                if(!chosenItems.contains(key)){
                    chosenItems.add(key);
                }
            }else{
                if(chosenItems.contains(key)){
                    chosenItems.remove(key);
                }
            }
            CharSequence txt = "No y!"+key.getName();
            Toast tst = Toast.makeText(ctxt, txt, Toast.LENGTH_LONG);
            //Log.i("chosenItemsTopass ", ""+chosenItems.size());
            //Log.i("chosenItems ", ""+chosenItems.get(0));
            tst.show();
        }else{
            //toast something went terribly wrong
            //CharSequence txt = "Something went terribly wrong!";
            //Toast tst = Toast.makeText(ctxt, txt, Toast.LENGTH_LONG);
            //tst.show();
        }
        //expt.getSelectedItemId();

    }
}
