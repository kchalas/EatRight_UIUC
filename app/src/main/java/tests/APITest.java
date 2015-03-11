package tests;

import android.test.InstrumentationTestCase;
import android.util.Log;

import java.util.ArrayList;

import api.WolframAPI;
import api.YelpAPI;
import api.apiInterface;
import information.MenuItem;
import information.RestaurantInfo;

public class APITest extends InstrumentationTestCase{
    public void testYelpAPI() throws Exception{
        ArrayList<RestaurantInfo> restaurantList = apiInterface.getRestaurants(40.11000, -88.22700);

        assertTrue(restaurantList.size() > 0);
    }

    public void testWolframAPI() throws Exception{
        MenuItem item = new MenuItem();
        item.setName("Big Mac");
        item.setRestaurantName("Mcdonalds");

        String xmlData = apiInterface.getNutritionInfo(item);

        assertTrue(xmlData.contains("success='true'"));
    }
}
