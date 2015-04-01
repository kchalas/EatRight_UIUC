package api;

import android.view.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import information.MenuItem;
import information.NutritionInfo;

public class MenuProvider {
    private HashMap<String, HashMap<String, List<String>>> menu;

    public MenuProvider(ArrayList<MenuItem> menuList){
        menu = new HashMap<String, HashMap<String, List<String>>>();
        HashMap<String, List<String>> small = new HashMap<String, List<String>>();
        HashMap<String, List<String>> medium = new HashMap<String, List<String>>();
        HashMap<String, List<String>> big = new HashMap<String, List<String>>();

        //List of details - List<String>
        for(MenuItem item : menuList){
            NutritionInfo itemInfo = apiInterface.getNutritionInfo(item);

            List<String> itemInfoList = new ArrayList<String>();
            itemInfoList.add(item.getRestaurantName());
            itemInfoList.add(Double.toString(itemInfo.getCalories()));
            itemInfoList.add(Double.toString(itemInfo.getFiber()));
            itemInfoList.add(Double.toString(itemInfo.getProtein()));


            if(itemInfo.getCalories() < 250){
                small.put(item.getName(), itemInfoList);
            }else if(itemInfo.getCalories() > 500){
                big.put(item.getName(), itemInfoList);
            }else{
                medium.put(item.getName(), itemInfoList);
            }
        }

        menu.put("Small", small);
        menu.put("Medium", medium);
        menu.put("Big", big);
    }

    public HashMap<String, HashMap<String, List<String>>> getMenu(){
        return menu;
    }

    public ArrayList<MenuItem> getCategory(String category){
        ArrayList<MenuItem> list = new ArrayList<MenuItem>();
        HashMap<String, List<String>> categoryList = menu.get(category);

        String[] itemNames = (String[])categoryList.keySet().toArray();
        for(String name : itemNames){
            MenuItem item = new MenuItem();
            item.setName(name);
            NutritionInfo info = new NutritionInfo();

            List<String> infoList = categoryList.get(name);
            item.setRestaurantName(infoList.get(0));
            info.setCalories(Double.parseDouble(infoList.get(1)));
            info.setFiber(Double.parseDouble(infoList.get(2)));
            info.setProtein(Double.parseDouble(infoList.get(3)));

            item.setNutritionInfo(info);

            list.add(item);
        }

        return list;
    }
}
