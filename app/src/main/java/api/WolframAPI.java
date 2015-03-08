package api;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilderFactory;

import information.MenuItem;

public class WolframAPI {

    private static String appid = "UVWUTA-LR7R5H2VTA";

    public static String getNutritionInfo(MenuItem item) throws IOException{
        String name = item.getName().replace(" ", "+");
        String restaurantName = item.getRestaurantName().replace(" ", "+");
        URL wolfram = new URL("http://api.wolframalpha.com/v2/query?input=" + restaurantName + "+" + name + "&appid=" + appid + "&includepodid=NutritionLabelSingle:ExpandedFoodData&podtimeout=10");
        URLConnection wolframConnection = wolfram.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(wolframConnection.getInputStream()));

        String line;
        StringBuffer response = new StringBuffer();
        while((line = in.readLine()) != null){
            response.append(line);
        }

        return response.toString();
    }
}
