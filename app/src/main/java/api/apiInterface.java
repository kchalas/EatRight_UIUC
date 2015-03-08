package api;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import information.*;

public class apiInterface {
    public static ArrayList<RestaurantInfo> getRestaurants(double lat, double lon) throws JSONException{
        String jsonData = YelpAPI.getRestaurants(lat, lon);

        return Parse.parseRestaurants(jsonData);
    }

    public static ArrayList<MenuItem> getMenu(String restaurantName){
        String jsonData = LocuAPI.getMenu(restaurantName);

        return Parse.parseMenu(jsonData);
    }

    public static NutritionInfo getNutritionInfo(MenuItem item) throws IOException{
        String xmlData = WolframAPI.getNutritionInfo(item);

        return Parse.parseNutritionInfo(xmlData);
    }
}
