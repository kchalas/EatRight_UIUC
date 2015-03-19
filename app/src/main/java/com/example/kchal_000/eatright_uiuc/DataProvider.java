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
    public static HashMap<String, HashMap<String, List<String>>> getData(){
        HashMap<String, HashMap<String, List<String>>> categories =
                new HashMap<String, HashMap<String, List<String>>>();
        String[] categs = {"Over 500 cal", "250 to 500 cal", "250 or less cal"};
        String[] entrees = {"Big Mac", "Chicken Nuggets", "Fish Fillet"};
        String[] sides = {"coleslaw", "salad", "potatoes", "fries"};
        String[] dessert = {"ice cream", "scones", "cookies", "pie"};
        List<String> details = new ArrayList<String>();
        details.add("Calories: 300");
        details.add("Protein: 5g");
        details.add("Dietary Fiber: 3g");
        details.add("Fat: 3g");
        List<String> detai = new ArrayList<String>();
        detai.add("Bull");
        detai.add("Crack");


        HashMap<String, List<String>> entrs = new HashMap<String, List<String>>();
        for(String e : entrees){
            entrs.put(e, details);
        }
        HashMap<String, List<String>> sds = new HashMap<String, List<String>>();
        for(String e : sides){
            sds.put(e, detai);
        }
        HashMap<String, List<String>> ds = new HashMap<String, List<String>>();
        int i = 0;
        for(String e : dessert){

            if(i%2 == 0) {
                ds.put(e, details);
            }else{
                ds.put(e, detai);
            }
            i++;
        }
        categories.put(categs[0], entrs);
        categories.put(categs[1], sds);
        categories.put(categs[2], ds);
        return categories;

    }
}
