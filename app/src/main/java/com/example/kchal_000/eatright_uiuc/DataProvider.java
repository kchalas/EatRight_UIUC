package com.example.kchal_000.eatright_uiuc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kchal_000 on 3/13/2015.
 */
public class DataProvider {
    public DataProvider(){
        //empty constructor
    }
    public HashMap<String, HashMap<String, List>> getData(){
        HashMap<String, HashMap<String, List>> categories = new HashMap<String, HashMap<String, List>>();
        String[] categs = {"entrees", "sides", "desserts", "drinks", "other"};
        String[] entrees = {"Big Mac", "Chicken Nuggets", "Fish Fillet"};
        String[] sides = {"coleslaw", "salad", "potatoes", "fries"};
        String[] dessert = {"ice cream", "scones", "cookies", "pie"};
        String[] drinks = {"coffee", "tea", "soda", "milk", "juice"};
        String[] other = {"extra sauce", "dressing"};
        List<String> details = new ArrayList<String>();
        details.add("Calories: 300");
        details.add("Protein: 5g");
        details.add("Dietary Fiber: 3g");
        details.add("Fat: 3g");

        HashMap<String, List> entrs = new HashMap<String, List>();
        for(String e : entrees){
            entrs.put(e, details);
        }
        HashMap<String, List> sds = new HashMap<String, List>();
        for(String e : sides){
            sds.put(e, details);
        }
        HashMap<String, List> ds = new HashMap<String, List>();
        for(String e : dessert){
            ds.put(e, details);
        }
        HashMap<String, List> dk = new HashMap<String, List>();
        for(String e : drinks){
            dk.put(e, details);
        }
        HashMap<String, List> ot = new HashMap<String, List>();
        for(String e : other){
            ot.put(e, details);
        }

        categories.put(categs[0], entrs);
        categories.put(categs[1], sds);
        categories.put(categs[2], ds);
        categories.put(categs[3], dk);
        categories.put(categs[4], ot);
        return categories;

    }
}
