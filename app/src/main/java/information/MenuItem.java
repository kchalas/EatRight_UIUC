package information;

public class MenuItem {
    private String name;
    private String restaurantName;

    public MenuItem(){}

    public MenuItem(String string){
        String[] strings = string.split("`");

        name = strings[0];
        restaurantName = strings[1];
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
}
