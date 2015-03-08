package information;

public class RestaurantInfo {
    private String name;
    private String address;
    private long distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
    public String toString(){
        return "name: " + getName() + ", address: "+ getAddress() + ", distance: " + getDistance();
    }
}
