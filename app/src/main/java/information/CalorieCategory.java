package information;

import java.util.ArrayList;

/**
 * Created by kchal_000 on 3/31/2015.
 */
public class CalorieCategory {
    private String name;
    private int maxCal;
    private int minCal;
    private boolean isSelected;
    //private ArrayList<MenuItem> menu;

    public CalorieCategory(String name, int max, int min){
        this.name = name;
        this.maxCal = max;
        this.minCal = min;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCal() {
        return maxCal;
    }

    public void setMaxCal(int maxCal) {
        this.maxCal = maxCal;
    }

    public int getMinCal() {
        return minCal;
    }

    public void setMinCal(int minCal) {
        this.minCal = minCal;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean inRange(int value){ return ((value >= this.minCal) &&( value <= this.maxCal));}
    /**
    public void setMenu(ArrayList<MenuItem> menu){ this.menu = menu;}


    public void toggleMenu(){
        //a toggle
        for(MenuItem m : menu){
            m.setSelected(!m.isSelected());
        }
    }**/

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        CalorieCategory category = (CalorieCategory)obj;

        return name.equals(category.getName());
    }
}

