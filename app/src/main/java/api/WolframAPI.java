package api;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilderFactory;

import information.MenuItem;

public class WolframAPI extends AsyncTask<MenuItem, Void, String> {

    private String appid = "UVWUTA-LR7R5H2VTA";

    @Override
    protected String doInBackground(MenuItem... items) {
        try {
            String name = items[0].getName().replace(" ", "+");
            String restaurantName = items[0].getRestaurantName().replace(" ", "+");
            URL wolfram = new URL("http://api.wolframalpha.com/v2/query?input=" + restaurantName + "+" + name + "&appid=" + appid + "&includepodid=NutritionLabelSingle:ExpandedFoodData&podtimeout=10");
            URLConnection wolframConnection = wolfram.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(wolframConnection.getInputStream()));

            String line;
            StringBuffer response = new StringBuffer();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            Log.v("wolfram_out", response.toString());
            return response.toString();
        }catch(IOException e){
            Log.e("error", e.toString());
        }catch(NullPointerException e){
            return null;
        }

        return "";
    }
}
