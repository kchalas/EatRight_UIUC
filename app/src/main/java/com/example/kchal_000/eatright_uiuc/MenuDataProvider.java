package com.example.kchal_000.eatright_uiuc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import api.apiInterface;
import information.MenuItem;
import information.NutritionInfo;
import information.RestaurantInfo;

/**
 * Created by kchal_000 on 3/19/2015.
 *
 * json to parse﹕ {"status": "success", "http_status": 200, "venues": []}
 */
public class MenuDataProvider {
    private RestaurantInfo rest;
    private String[] categs = {"Over 750 cal", "500 to 750 cal", "250 to 500 cal", "250 or less cal"};

    public MenuDataProvider(RestaurantInfo rI){
        this.rest = rI;
        //need to get a menu.


        //assume that you get an arraylist of menuitems
        HashMap<MenuItem, NutritionInfo> nutritionOfItems = new HashMap<MenuItem, NutritionInfo>();
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
        MenuItem a = new MenuItem();
        a.setName("Big Mac");
        a.setRestaurantName("McDonalds");
        MenuItem b = new MenuItem();
        b.setName("French Fry");
        b.setRestaurantName("McDonalds");
        menu.add(a);
        menu.add(b);

        for(MenuItem m : menu){
            try {
                NutritionInfo nutritionForItem = apiInterface.getNutritionInfo(m);
                nutritionOfItems.put(m, nutritionForItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
