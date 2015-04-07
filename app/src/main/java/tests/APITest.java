package tests;

import android.test.InstrumentationTestCase;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;

import api.MenuProvider;
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

    public void testMenuProvider() throws Exception {
        MenuItem item = new MenuItem();
        item.setName("Whopper");
        item.setRestaurantName("Burger King");

        ArrayList<MenuItem> itemList = new ArrayList<>();
        itemList.add(item);

        MenuProvider provider = new MenuProvider(itemList);

        ArrayList<MenuItem> returnedList = provider.getCategory(provider.getCategories().get(1));

        assertTrue(returnedList.size() > 0);
        assertTrue(returnedList.get(0).getName().equals("Whopper"));
    }

    public void testLocuAPI() throws Exception {
        RestaurantInfo restaurant = new RestaurantInfo();
        restaurant.setName("Burger King");
        restaurant.setLat(40.11);
        restaurant.setLon(-88.227);

        MenuProvider menuProvider = apiInterface.getMenu(restaurant);

        ArrayList<MenuItem> smallItems = menuProvider.getCategory(menuProvider.getCategories().get(1));

        assertTrue(smallItems.size() > 0);
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
