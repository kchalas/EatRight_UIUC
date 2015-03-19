package information;

import java.io.Serializable;

public class RestaurantInfo implements Serializable {
    private String name;
    private String address;
    private long distance;
    private double lat;
    private double lon;

    public RestaurantInfo(){}

    public RestaurantInfo(String string){
        String[] strings = string.split("`");

        name = strings[0];
        address = strings[1];
        distance = Long.parseLong(strings[2]);
        lat = Double.parseDouble(strings[3]);
        lon = Double.parseDouble(strings[4]);
    }

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

    public double getLon() { return lon; }

    public void setLon(double lon) { this.lon = lon; }

    public double getLat() { return lat; }

    public void setLat(double lat) { this.lat = lat; }

    public String toString(){
        return name + "`" + address + "`" + distance + "`" + lat + "`" + lon;
    }
}
