package tests;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.example.kchal_000.eatright_uiuc.RestaurantChoices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import api.MenuProvider;
import api.apiInterface;
import information.CalorieCategory;
import information.MenuItem;
import information.RestaurantInfo;

/**
 * Created by kchal_000 on 4/27/2015.
 * Can find Google Maps GPS coordinates with the below website
 * http://universimmedia.pagesperso-orange.fr/geo/loc.htm
 *
 *
 *  RestaurantChoices rc = new RestaurantChoices();
 double[] loc = rc.getLocation();
 //siebel location 40.11380	-88.22491
 Log.i("gps", ""+loc[0]+ " "+loc[1]);
 assert(loc[0] == 40.11380);
 assert(loc[1]== -88.22491);
 *
 */
public class DataTest  extends InstrumentationTestCase {



    //test gps - find correct restaurants
    public void testFindingRestaurantAAtGPS() throws Exception {
        //gps location for taco bell
        ArrayList<RestaurantInfo> temp = apiInterface.getRestaurants(40.11676, -88.22110);
        ArrayList<String> rest_names = new ArrayList<String>();
        for(int  i = 0; i < temp.size(); i++) {
            rest_names.add(temp.get(i).getName());
            Log.i("arrayList", "" + temp.get(i).getName());
        }
        assert(rest_names.contains("Taco Motorizado"));
        assert(rest_names.contains("Taco Bell"));
    }

    public void testFindingRestaurantBAtGPS() throws Exception {
        //607 N Cunningham Ave, Urbana, IL 61802 Taco Motorizado
        //right next to Arby's and McDonald's
        ArrayList<RestaurantInfo> temp = apiInterface.getRestaurants(40.11862, -88.20411);
        ArrayList<String> rest_names = new ArrayList<String>();
        for(int  i = 0; i < temp.size(); i++) {
            rest_names.add(temp.get(i).getName());
            Log.i("arrayList", "" + temp.get(i).getName());
        }
        assert(rest_names.contains("Taco Motorizado"));
        assert(rest_names.contains("Arby's"));
        assert(rest_names.contains("McDonald's"));
    }


    //get a json menu , print and parse
    public void testJSONMenu(){
        //some menu items in JSON - Frozen Strawberry Lemonade ; Beefy 5-Layer Burrito  Chicken Soft Taco
        RestaurantInfo ri = new RestaurantInfo("Taco Bell`616 E Green St Champaign IL`10`40.11056`-88.22955");
        MenuProvider menu = apiInterface.getMenu(ri);
        HashMap<CalorieCategory, HashMap<MenuItem,List<String>> > mm = menu.getMenu();
        Object[] keys =  mm.keySet().toArray();
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        for(Object k : keys){
            HashMap<MenuItem, List<String>> dataToSelect = mm.get(k);
            Object[] keys2 = dataToSelect.keySet().toArray();
            //get a list of items to select correctly
            for(Object k2 : keys2){
                items.add((MenuItem)k2); //should end up will all items
            }
        }
        assert(items.size() > 0);
        assert(!items.get(0).getName().equals(items.get(1).getName()));

    }




}
