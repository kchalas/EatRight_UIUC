package tests;

import android.test.InstrumentationTestCase;
import android.util.Log;

import java.util.ArrayList;

import api.WolframAPI;
import api.YelpAPI;
import api.apiInterface;
import information.MenuItem;
import information.NutritionInfo;
import information.RestaurantInfo;

public class APITest extends InstrumentationTestCase{
    public void testYelpAPI() throws Exception{
        ArrayList<RestaurantInfo> restaurantList = apiInterface.getRestaurants(40.11000, -88.22700);

        assertTrue(restaurantList.size() > 0);
    }

    public void testWolframAPI() throws Exception{
        MenuItem item = new MenuItem();
        item.setName("chicken nugget");
        item.setRestaurantName("Mcdonalds");

        NutritionInfo info = apiInterface.getNutritionInfo(item);

        assertTrue(info.getCalories() == 46.0);
        assertTrue(info.getFiber() == 0.127);
        assertTrue(info.getProtein() == 2.0);
    }
}
