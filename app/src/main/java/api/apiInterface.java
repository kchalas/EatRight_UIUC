package api;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import information.*;

public class apiInterface {
    public static ArrayList<RestaurantInfo> getRestaurants(double lat, double lon) {
        YelpAPI apiTask = new YelpAPI();

        String jsonData = "";
        try {
            jsonData = apiTask.execute(new Double(lat), new Double(lon)).get();
        }catch(InterruptedException e){
            Log.e("error", e.toString());
        }catch(ExecutionException e){
            Log.e("error", e.toString());
        }

        return Parse.parseRestaurants(jsonData);
    }

    public static ArrayList<MenuItem> getMenu(String restaurantName){
        String jsonData = LocuAPI.getMenu(restaurantName);

        return Parse.parseMenu(jsonData);
    }

    public static NutritionInfo getNutritionInfo(MenuItem item) throws IOException{

        WolframAPI apiTask = new WolframAPI();

        String xmlData = "";
        try {
            xmlData = apiTask.execute(item).get();
        }catch(InterruptedException e){
            Log.e("error", e.toString());
        }catch(ExecutionException e){
            Log.e("error", e.toString());
        }

        NutritionInfo info = Parse.parseNutritionInfo(xmlData);
        info.setName(item.getName());

        return info;
    }
}
