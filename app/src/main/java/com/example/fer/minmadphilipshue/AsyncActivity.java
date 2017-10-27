package com.example.fer.minmadphilipshue;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fer.minmadphilipshue.domain.PhilipsHueLamp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import static android.content.ContentValues.TAG;

/**
 * Created by ferdinand on 11-10-2017.
 */

public class AsyncActivity extends AsyncTask<String, Void, String> {
    private OnLampAvailable listener;
    private String getUrl = "http://192.168.1.179/api/lXPypsuuj4ujhAlXdZP2URMJpVZwqUS-HAMrojC0/lights";

    public AsyncActivity(OnLampAvailable listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {

        InputStream inputStream = null;
        BufferedReader reader = null;
        String urlString = "";
        String response = "";

        try {
            // URL url = new URL(strings[0]);
            URL url = new URL(getUrl);
            URLConnection connection = url.openConnection();

            reader = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            response = reader.readLine().toString();
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
        } catch (Exception e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("TAG", e.getLocalizedMessage());
                    return null;
                }
            }
        }

        System.out.println("response = " + response);
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        // Check of er een response is
        if(response == null || response == "") {
            Log.e(TAG, "onPostExecute kreeg een lege response!");
            return;
        }
        JSONObject jsonObject;
        try {
            // Top level json object
            jsonObject = new JSONObject(response);
            // Get all users and start looping
            Iterator<String> lampHeaders = jsonObject.keys();

            while(lampHeaders.hasNext()){
                String id = lampHeaders.next();
                JSONObject lampObject = jsonObject.getJSONObject(id);
                    JSONObject lampState = lampObject.getJSONObject("state");
                        boolean isOn = lampState.getBoolean("on");
                        int bri = lampState.getInt("bri");
                        int hue = lampState.getInt("hue");
                        int sat = lampState.getInt("sat");
                    String name = lampObject.getString("name");

                PhilipsHueLamp lamp = new PhilipsHueLamp(id, isOn, bri, hue, sat, name);
                listener.onLampAvailable( lamp );

            }
        } catch( JSONException ex) {
            Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
    }
}
