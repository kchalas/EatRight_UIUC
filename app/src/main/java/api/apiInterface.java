package api;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import information.*;

/**
 * Provides an easy to use interface to the various APIs
 */
public class apiInterface {

    /**
     * @param lat
     * @param lon
     * @return ArrayList<RestaurantInfo>
     * Calls Yelp using the given coordinates, then parses the returned json object into a list of restaurants.
     */
    public static ArrayList<RestaurantInfo> getRestaurants(double lat, double lon) {
        if(Math.abs(lat) > 180 || Math.abs(lon) > 180){
            return null;
        }

        YelpAPI apiTask = new YelpAPI();

        String jsonData = "";
        try {
            jsonData = apiTask.execute(new Double(lat), new Double(lon)).get();
        }catch(InterruptedException e){
            Log.e("error", e.toString());
        }catch(ExecutionException e){
            Log.e("error", e.toString());
        }

        Log.v("yelpOut", jsonData);
        return Parse.parseRestaurants(jsonData, lat, lon);
    }

    /**
     * @param restaurant
     * @return MenuProvider
     * Calls Locu using the given restaurant name and coordinates.
     * Parses the returned json object into a list of menu items.
     * Creates a MenuProvider object to store and sort the returned items.
     */
    public static MenuProvider getMenu(RestaurantInfo restaurant){
        LocuAPI apiTask = new LocuAPI();
        String jsonData = "";
        try {
            jsonData = apiTask.execute(restaurant).get();
        }catch(InterruptedException e){
            Log.e("error", e.toString());
        }catch(ExecutionException e){
            Log.e("error", e.toString());
        }

        int maxLogSize = 1000;
        for(int i = 0; i <= jsonData.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i+1) * maxLogSize;
            end = end > jsonData.length() ? jsonData.length() : end;
            Log.v("locu_output", jsonData.substring(start, end));
        }

        ArrayList<MenuItem> itemList = Parse.parseMenu(jsonData);

        if(itemList == null) {
            return new MenuProvider(new ArrayList<MenuItem>());
        }else{
            for(MenuItem item : itemList){
                item.setRestaurantName(restaurant.getName());
                Log.i("items w/ rest", item.toString());
            }
        }

        Log.i("menu items", ""+itemList);
        return new MenuProvider(itemList);
    }

    /**
     * @param item
     * @return String
     * Calls Wolfram using the given item name and restaurant to which it belongs to.
     * Parses the returned xml object to find the nutritional information of the item.
     */
    public static NutritionInfo getNutritionInfo(MenuItem item){

        WolframAPI apiTask = new WolframAPI();

        String xmlData = "";
        try {
            xmlData = apiTask.execute(item).get();
        }catch(InterruptedException e){
            Log.e("error", e.toString());
        }catch(ExecutionException e){
            Log.e("error", e.toString());
        }

        if(xmlData == null){
            return null;
        }

        NutritionInfo info = Parse.parseNutritionInfo(xmlData);

        if(info == null){
            return null;
        }

        info.setName(item.getName());

        return info;
    }
}
