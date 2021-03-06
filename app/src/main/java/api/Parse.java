package api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import information.*;


/**
 * Holds the various parsing functions to parse the data from locu and yelp and wolfram.
 */
public class Parse {
    /**
     * @param jsonData
     * @param lat
     * @param lon
     * @return ArrayList<RestaurantInfo>
     *     This function takes the jsonData lat and lon (which is data taken from the yelp api)
     *     parses the data and extracts the names of the restaurants from the Json data.
     *     Restaurant names,lon and lat are added to a ResturantInfo object and the Resturant objects are pushed onto the ArrayList
     */
    public static ArrayList<RestaurantInfo> parseRestaurants(String jsonData, double lat, double lon){
        try {
            ArrayList<RestaurantInfo> restList = new ArrayList<RestaurantInfo>();
            JSONObject obj = new JSONObject(jsonData);

            int n = obj.getInt("total");

            JSONArray business = obj.getJSONArray("businesses");

            for (int i = 0; i < business.length(); i++) {
                RestaurantInfo entry = new RestaurantInfo();
                String name = business.getJSONObject(i).getString("name");
                JSONObject loc = business.getJSONObject(i).getJSONObject("location");

                entry.setAddress(loc.getJSONArray("display_address").toString());
                entry.setName(name);

                entry.setLat(lat);
                entry.setLon(lon);

                restList.add(entry);
            }



            return restList;
        }catch(JSONException e){
            Log.e("error", e.toString());
        }

        return null;
    }
    /**
     *
     * @param jsonData
     * @return ArrayList<MenuItem>
     *     This function takes the jsonData (which is data taken from the locu api) string
     *     parses the data and extracts the names of the meals for each venue.
     *     Meal names are added to a menuItems object and the menuItems are pushed onto the ArrayList
     *
     */
    public static ArrayList<MenuItem> parseMenu(String jsonData){
        try {
            ArrayList<MenuItem>  menuList = new ArrayList<MenuItem> ();
            MenuItem entry;
            JSONObject obj = new JSONObject(jsonData);
            JSONArray venues = obj.getJSONArray("venues");
            System.out.println(venues.toString());
            for (int l = 0;l<venues.length();l++) {

                if(venues.getJSONObject(l).isNull("menus")){
                    continue;
                }JSONArray menus = venues.getJSONObject(l).getJSONArray("menus");

                for (int z = 0; z < menus.length(); z++) {
                  JSONArray sections = menus.getJSONObject(z).getJSONArray("sections");

                    for (int i = 0; i < sections.length(); i++) {
                            JSONArray subsections = sections.getJSONObject(i).getJSONArray("subsections");

                            for (int j = 0; j < subsections.length(); j++) {
                                    JSONArray contents = subsections.getJSONObject(j).getJSONArray("contents");
                                    for (int k = 0; k < contents.length(); k++) {
                                            entry = new MenuItem();
                                            String name = contents.getJSONObject(k).get("name").toString();
                                            entry.setName(name);
                                            menuList.add(entry);
                                    }
                            }

                     }

                }
            }
                return menuList;
        }
        catch(JSONException e){
            Log.e("error", e.toString());
        }

        return null;
    }

    /**
     * @param xmlData
     * @return NutritionInfo
     * Takes an xml file containing the nutrition info of an item from Wolfram and returns the item's
     * calorie, protein, and dietary fiber content.
     */
    public static NutritionInfo parseNutritionInfo(String xmlData){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document xmlDoc = builder.parse(new InputSource(new StringReader(xmlData)));

            xmlDoc.getDocumentElement().normalize();

            NodeList list = xmlDoc.getElementsByTagName("pod");
            Element pod = (Element)list.item(0);

            list = pod.getElementsByTagName("subpod");
            Element subpod = (Element)list.item(0);

            list = subpod.getElementsByTagName("plaintext");
            Node plainText = list.item(0);

            //Get values from nutrition string
            String info = plainText.getTextContent();
            NutritionInfo itemInfo = new NutritionInfo();

            int index = info.indexOf("calories ")+10;
            String intermediate = info.substring(index, info.indexOf('%', index));
            Log.i("interm", intermediate);
            int calories;
            if(intermediate.contains("|")) {
                String dim = intermediate.substring(0, intermediate.indexOf('|')-1);
                Log.i("bar", dim);
                calories = Integer.parseInt(intermediate.substring(0, intermediate.indexOf('|')-1));
            }else{
                //Log.i("came to here!!", intermediate.substring(0, intermediate.indexOf('|')-1));
                calories = Integer.parseInt(intermediate);
            }

            itemInfo.setCalories(calories);

            itemInfo.setFiber(getGrams("dietary fiber ", info));

            itemInfo.setProtein(getGrams("protein ", info));

            return itemInfo;
        }catch(ParserConfigurationException e){
            Log.e("error", e.toString());
        }catch(SAXException e){
            Log.e("error", e.toString());
        }catch(IOException e){
            Log.e("error", e.toString());
        }catch(NullPointerException e){
            return null;
        }

        return null;
    }

    private static double getGrams(String attribute, String info){
        int index = info.indexOf(attribute) + attribute.length();
        String substring = info.substring(index, info.indexOf('g', index));
        double value;
        if(substring.contains("m")){
            value = Double.parseDouble(substring.substring(0,substring.length()-2)) / 1000.0;
        }else{
            value = Double.parseDouble(substring.substring(0, substring.length()-1));
        }
        return value;
    }
}
