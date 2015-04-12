package api;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import information.*;

public class apiInterface {
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

        return Parse.parseRestaurants(jsonData, lat, lon);
    }

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

        ArrayList<MenuItem> itemList = Parse.parseMenu(jsonData);

        if(itemList == null) {
            return null;
        }

        return new MenuProvider(itemList);
    }

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

        NutritionInfo info = Parse.parseNutritionInfo(xmlData);

        if(info == null){
            return null;
        }

        info.setName(item.getName());

        return info;
    }
}
