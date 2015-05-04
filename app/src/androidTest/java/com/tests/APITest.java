package com.tests;

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

/**
 * Tests various functionalities about the api package
 */
public class APITest extends InstrumentationTestCase{
    /**
     * Check if Yelp returns correctly
     */
    public void testYelpAPI() throws Exception{
        ArrayList<RestaurantInfo> restaurantList = apiInterface.getRestaurants(40.11000, -88.22700);

        assertTrue(restaurantList.size() > 0);
    }

    /**
     * Check what happens on a bad input
     */
    public void testYelpAPIBadInputs() throws Exception{
        ArrayList<RestaurantInfo> restaurantList = apiInterface.getRestaurants(400, -88.22700);

        assertTrue(restaurantList == null);

        restaurantList = apiInterface.getRestaurants(40.11000, -400);

        assertTrue(restaurantList == null);
    }

    /**
     * Check that the menu provider correctly sets up the categories
     */
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

    /**
     * Check that the menu provider correctly returns empty with an empty menu
     */
    public void testMenuProviderEmpty() throws Exception {
        ArrayList<MenuItem> itemList = new ArrayList<>();

        MenuProvider provider = new MenuProvider(itemList);

        ArrayList<MenuItem> returnedList = provider.getCategory(provider.getCategories().get(1));

        assertTrue(returnedList.isEmpty());
    }

    /**
     * Check that locu can return a menu and the menu provider and store it
     */
    public void testLocuAPI() throws Exception {
        RestaurantInfo restaurant = new RestaurantInfo();
        restaurant.setName("Burger King");
        restaurant.setLat(40.11);
        restaurant.setLon(-88.227);

        MenuProvider menuProvider = apiInterface.getMenu(restaurant);

        ArrayList<MenuItem> smallItems = menuProvider.getCategory(menuProvider.getCategories().get(1));

        assertTrue(smallItems.size() > 0);
    }

    /**
     * Check that locu is not called on a bad input
     */
    public void testLocuAPIBadInput() throws Exception {
        RestaurantInfo restaurant = new RestaurantInfo();
        restaurant.setName("Bad Input");
        restaurant.setLat(40.11);
        restaurant.setLon(-88.227);

        MenuProvider menuProvider = apiInterface.getMenu(restaurant);

        assertTrue(menuProvider.isEmpty());
    }

    /**
     * Check that Wolfram returns correctly
     */
    public void testWolframAPI() throws Exception{
        MenuItem item = new MenuItem();
        item.setName("chicken nugget");
        item.setRestaurantName("Mcdonalds");

        NutritionInfo info = apiInterface.getNutritionInfo(item);

        assertTrue(info.getCalories() == 46.0);
        assertTrue(info.getFiber() == 0.127);
        assertTrue(info.getProtein() == 2.0);
    }

    /**
     * Check that Wolfram can not find anything on a bad input
     */
    public void testWolframAPIBadInput() throws Exception{
        MenuItem item = new MenuItem();
        item.setName("Bad Input");
        item.setRestaurantName("Bad Input");

        NutritionInfo info = apiInterface.getNutritionInfo(item);

        assertTrue(info == null);
    }
}
