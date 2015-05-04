package information;

import com.example.kchal_000.eatright_uiuc.Meal;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String name;
    private String restaurantName;
    private NutritionInfo nutritionInfo;
    private boolean selected = false;

    public MenuItem(){}

    /**
     * Hold the details of meals and allow for meals to be chosen.
     * Know whether an individual meal has been selected with a checkbox.
     * @param string
     */
    public MenuItem(String string){
        String[] strings = string.split("`");

        name = strings[0];
        restaurantName = strings[1];
    }

    public MenuItem(Meal meal){
        name=meal.getId();
        restaurantName=meal.getSource();
        nutritionInfo=new NutritionInfo(meal.getFiber(),meal.getProtein(),meal.getCalories(),name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String toString(){
        return name + "`" + restaurantName;
    }

    public void setNutritionInfo(NutritionInfo info){
        nutritionInfo = info;
    }

    public NutritionInfo getNutritionInfo(){
        return nutritionInfo;
    }

    public boolean isSelected(){return selected; }

    public void setSelected(boolean selected){this.selected = selected;}

    @Override
    public int hashCode(){
        return (name + restaurantName).hashCode();
    }

    @Override
    public boolean equals(Object obj){
        MenuItem item = (MenuItem)obj;

        return name.equals(item.getName()) && restaurantName.equals(item.getRestaurantName());
    }
}
