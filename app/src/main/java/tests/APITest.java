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
        String jsonData = YelpAPI.getRestaurants(40.11000, -88.22700);
        Log.v("ds", jsonData);
        assertFalse(jsonData.contains("error"));
    }

    public void testWolframAPI() throws Exception{
        MenuItem item = new MenuItem();
        item.setName("Big Mac");
        item.setRestaurantName("Mcdonalds");

        String xmlData = WolframAPI.getNutritionInfo(item);

        assertTrue(xmlData.contains("success='true'"));
    }
}
