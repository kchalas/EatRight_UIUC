package api;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import information.RestaurantInfo;

public class LocuAPI extends AsyncTask<RestaurantInfo, Void, String> {

    private String appid = "aafec4f8e49e8554b7349777f57f01b51b916b9d";

    @Override
    protected String doInBackground(RestaurantInfo... rests){
        try {
            RestaurantInfo restaurant = rests[0];

            JSONObject json = createJSON(restaurant);

            URL url = new URL("https://api.locu.com/v2/venue/search");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(json.toString());
            writer.flush();

            int responseCode = connection.getResponseCode();
            if(responseCode == 200){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

                String line;
                StringBuffer response = new StringBuffer();
                while((line = in.readLine()) != null){
                    response.append(line);
                }

                return response.toString();
            }else{
                Log.e("response_code", Integer.toString(responseCode));
            }
        }catch(ProtocolException e){
            Log.e("error", e.toString());
        }catch(MalformedURLException e){
            Log.e("error", e.toString());
        }catch(IOException e){
            Log.e("error", e.toString());
        }

        return "";
    }

    private JSONObject createJSON(RestaurantInfo restaurant){
        try {
            JSONObject json = new JSONObject();

            json.put("api_key", appid);

            JSONArray jsonArr = new JSONArray();
            jsonArr.put("name");
            jsonArr.put("menus");
            json.put("fields", jsonArr);

            JSONObject queryObject = new JSONObject();
            if(restaurant != null) {
                queryObject.put("name", restaurant.getName());
            }
            jsonArr = new JSONArray();
            jsonArr.put(restaurant.getLat());
            jsonArr.put(restaurant.getLon());
            jsonArr.put(10000);
            JSONObject locObject = new JSONObject();
            locObject.put("$in_lat_lng_radius", jsonArr);
            JSONObject loc2Object = new JSONObject();
            loc2Object.put("geo", locObject);
            queryObject.put("location", loc2Object);
            jsonArr = new JSONArray();
            jsonArr.put(queryObject);

            json.put("venue_queries", jsonArr);

            jsonArr = new JSONArray();
            queryObject = new JSONObject();
            queryObject.put("type", "ITEM");
            jsonArr.put(queryObject);

            //json.put("menu_item_queries", jsonArr);
            Log.v("json", json.toString());
            return json;

        }catch(JSONException e){
            Log.e("error", e.toString());
        }

        return null;
    }
}
