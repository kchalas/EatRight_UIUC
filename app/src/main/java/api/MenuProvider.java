package api;

import android.view.Menu;

import com.example.kchal_000.eatright_uiuc.DataProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import information.CalorieCategory;
import information.MenuItem;
import information.NutritionInfo;

public class MenuProvider {
    private HashMap<CalorieCategory, HashMap<MenuItem, List<String>>> menu;
    private ArrayList<CalorieCategory> categories;
    private boolean empty;

    public MenuProvider(ArrayList<MenuItem> menuList){
        empty = true;

        menu = new HashMap<>();
        HashMap<MenuItem, List<String>> small = new HashMap<>();
        HashMap<MenuItem, List<String>> medium = new HashMap<>();
        HashMap<MenuItem, List<String>> big = new HashMap<>();
        HashMap<MenuItem, List<String>> veryBig = new HashMap<>();

        //List of details - List<String>
        for(MenuItem item : menuList){
            NutritionInfo itemInfo = apiInterface.getNutritionInfo(item);

            if(itemInfo != null) {
                List<String> itemInfoList = new ArrayList<>();
                itemInfoList.add(item.getName());
                itemInfoList.add(item.getRestaurantName());
                itemInfoList.add(Double.toString(itemInfo.getCalories()));
                itemInfoList.add(Double.toString(itemInfo.getFiber()));
                itemInfoList.add(Double.toString(itemInfo.getProtein()));

                item.setNutritionInfo(itemInfo);
                if (itemInfo.getCalories() < 250) {
                    small.put(item, itemInfoList);
                } else if (itemInfo.getCalories() >= 250 && itemInfo.getCalories() < 500) {
                    medium.put(item, itemInfoList);
                } else if (itemInfo.getCalories() >= 750) {
                    veryBig.put(item, itemInfoList);
                } else {
                    big.put(item, itemInfoList);
                }

                empty = false;
            }
        }

        //Create categories
        categories = new ArrayList<>();
        categories.add(new CalorieCategory("Very High Cal - 750 or more ", 50000, 750));
        categories.add(new CalorieCategory("High Cal - 500 to 749", 749, 500));
        categories.add(new CalorieCategory("Medium Cal - 250 to 499", 499, 250));
        categories.add(new CalorieCategory("Low Cal - 249 or less", 249, 0));

        menu.put(categories.get(0), veryBig);
        menu.put(categories.get(1), big);
        menu.put(categories.get(2), medium);
        menu.put(categories.get(3), small);
    }

    public HashMap<CalorieCategory, HashMap<MenuItem, List<String>>> getMenu(){
        return menu;
    }

    public ArrayList<CalorieCategory> getCategories(){
        return categories;
    }

    public ArrayList<MenuItem> getCategory(CalorieCategory category){
        ArrayList<MenuItem> list = new ArrayList<>();
        HashMap<MenuItem, List<String>> categoryList = menu.get(category);

        Iterator it = categoryList.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            List<String> infoList = (List<String>)pair.getValue();

            MenuItem item = new MenuItem();
            NutritionInfo info = new NutritionInfo();

            item.setName(infoList.get(0));
            item.setRestaurantName(infoList.get(1));
            info.setCalories(Double.parseDouble(infoList.get(2)));
            info.setFiber(Double.parseDouble(infoList.get(3)));
            info.setProtein(Double.parseDouble(infoList.get(4)));

            item.setNutritionInfo(info);

            list.add(item);
        }

        return list;
    }

    public ArrayList<MenuItem> getCheckedItems(){
        ArrayList<MenuItem> list = new ArrayList<>();

        for(CalorieCategory category : categories){
            ArrayList<MenuItem> categoryList = getCategory(category);

            if(category.isSelected()){
                list.addAll(categoryList);
            }else {
                if (categoryList != null){
                    for (MenuItem item : categoryList) {
                        if (item.isSelected()) {
                            list.add(item);
                        }
                    }
                }
            }
        }

        return list;
    }

    public boolean isEmpty(){
        return empty;
    }
}