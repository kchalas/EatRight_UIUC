package api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import information.*;



public class Parse {
    public static ArrayList<RestaurantInfo> parseRestaurants(String xmlData) throws org.json.JSONException{
        ArrayList<RestaurantInfo> restList = new ArrayList<RestaurantInfo>();
        JSONObject obj = new JSONObject(xmlData);
        System.out.println(obj.toString());
        int n = obj.getInt("total");
        //String [] bus= obj.getNames(obj);
        JSONArray business = obj.getJSONArray("businesses");
        /*for (int i=0; i<bus.length;i++) {
            System.out.println(bus[i]);
        }*/
        for (int i=0; i<business.length();i++) {
            RestaurantInfo entry = new RestaurantInfo();
            String name = business.getJSONObject(i).getString("name");
            JSONObject loc=business.getJSONObject(i).getJSONObject("location");
            System.out.println(name);
            entry.setAddress(loc.getJSONArray("display_address").toString());
            entry.setName(name);
            restList.add(entry);
        }
        return restList;
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
