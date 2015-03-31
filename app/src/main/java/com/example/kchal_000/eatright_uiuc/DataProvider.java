package com.example.kchal_000.eatright_uiuc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import information.MenuItem;

/**
 * Created by kchal_000 on 3/13/2015.
 */
public class DataProvider {
    public DataProvider(){
        //empty constructor
    }
    public static HashMap<String, HashMap<MenuItem, List<String>>> getData(){
        HashMap<String, HashMap<MenuItem, List<String>>> categories =
                new HashMap<String, HashMap<MenuItem, List<String>>>();
        String[] categs = {"Over 750 cal", "500 to 750 cal", "250 to 500 cal", "250 or less cal"};
        MenuItem[] entrees = {new MenuItem("Big Mac`McDonalds"),
                new MenuItem("Chicken Nuggets`McDonalds"), new MenuItem("Fish Fillet`McDonalds")};
        MenuItem[] sides = {new MenuItem("coleslaw`McDonalds"), new MenuItem("salad`McDonalds"),
                new MenuItem("potatoes`McDonalds"), new MenuItem("fries`McDonalds")};
        MenuItem[] dessert = {new MenuItem("ice cream`McDonalds"), new MenuItem("scones`McDonalds"),
                new MenuItem("cookies`McDonalds"), new MenuItem("pie`McDonalds")};

        List<String> details = new ArrayList<String>();
        details.add("Calories: 300");
        details.add("Protein: 5g");
        details.add("Dietary Fiber: 3g");
        details.add("Fat: 3g");

        List<String> detai = new ArrayList<String>();
        detai.add("Bull");
        detai.add("Crack");


        HashMap<MenuItem, List<String>> entrs = new HashMap<MenuItem, List<String>>();
        for(MenuItem e : entrees){
            entrs.put(e, details);
        }
        HashMap<MenuItem, List<String>> sds = new HashMap<MenuItem, List<String>>();
        for(MenuItem e : sides){
            sds.put(e, detai);
        }
        HashMap<MenuItem, List<String>> ds = new HashMap<MenuItem, List<String>>();
        int i = 0;
        for(MenuItem e : dessert){

            if(i%2 == 0) {
                ds.put(e, details);
            }else{
                ds.put(e, detai);
            }
            i++;
        }
        HashMap<MenuItem, List<String>> vs = new HashMap<>();
        vs.put(new MenuItem("pepsi`McDonalds"), details);

        categories.put(categs[0], entrs);
        categories.put(categs[1], sds);
        categories.put(categs[2], ds);
        categories.put(categs[3], vs);
        return categories;

    }
}
