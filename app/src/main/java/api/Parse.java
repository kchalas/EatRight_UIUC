package api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import information.*;



public class Parse {
    public static ArrayList<RestaurantInfo> parseRestaurants(String xmlData){
        try {
            ArrayList<RestaurantInfo> restList = new ArrayList<RestaurantInfo>();
            JSONObject obj = new JSONObject(xmlData);

            int n = obj.getInt("total");

            JSONArray business = obj.getJSONArray("businesses");

            for (int i = 0; i < business.length(); i++) {
                RestaurantInfo entry = new RestaurantInfo();
                String name = business.getJSONObject(i).getString("name");
                JSONObject loc = business.getJSONObject(i).getJSONObject("location");

                entry.setAddress(loc.getJSONArray("display_address").toString());
                entry.setName(name);
                restList.add(entry);
            }
            return restList;
        }catch(JSONException e){
            Log.e("error", e.toString());
        }

        return null;
    }

    public static ArrayList<MenuItem> parseMenu(String xmlData){
        //TO-DO

        return null;
    }

    public static NutritionInfo parseNutritionInfo(String xmlData){
        //TO-DO

        return null;
    }
}
