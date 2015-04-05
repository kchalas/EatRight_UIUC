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



public class Parse {
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

    public static ArrayList<MenuItem> parseMenu(String jsonData){
        try {
            ArrayList<MenuItem>  menuList = new ArrayList<MenuItem> ();
            JSONObject obj = new JSONObject(jsonData);

            JSONArray sections = obj.getJSONArray("sections");

            for (int i = 0; i < sections.length(); i++) {
                JSONArray subsections = sections.getJSONObject(i).getJSONArray("subsections");
                for (int j = 0; j < subsections.length(); j++) {
                    JSONArray contents = subsections.getJSONObject(j).getJSONArray("contents");
                    MenuItem entry = new MenuItem();
                    for (int k = 0; k < subsections.length(); k++) {
                        String name = contents.getJSONObject(k).getString("name");
                        entry.setName(name);
                        menuList.add(entry);
                    }
                }
            }
            return menuList;
        }catch(JSONException e){
            Log.e("error", e.toString());
        }

        return null;
    }

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
            int calories = Integer.parseInt(info.substring(index, info.indexOf('%', index)));
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
