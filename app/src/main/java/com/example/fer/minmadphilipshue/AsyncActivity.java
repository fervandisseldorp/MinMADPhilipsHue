package com.example.fer.minmadphilipshue;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fer.minmadphilipshue.domain.PhilipsHueLamp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static android.content.ContentValues.TAG;

/**
 * Created by ferdinand on 11-10-2017.
 */

public class AsyncActivity extends AsyncTask<String, Void, String> {
    private OnLampAvailable listener;

    public AsyncActivity(OnLampAvailable listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {
        System.out.println("reached do in background");


        InputStream inputStream = null;
        BufferedReader reader = null;
        String urlString = "";
        String response = "";

        try {
            URL url = new URL(strings[0]);
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

        return response;
    }


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
            JSONArray lamps = jsonObject.getJSONArray("results");
            for(int idx = 0; idx < lamps.length(); idx++) {
                // array level objects and get user
                JSONObject lamp = lamps.getJSONObject(idx);

                // Get title, first and last name
                JSONObject name = lamp.getJSONObject("name");
                String title = name.getString("title");
                String firstName = name.getString("first");

                // Create new Person object
                PhilipsHueLamp p = new PhilipsHueLamp();
                p.setName("testlamp");
                //
                // call back with new person data
                //
                listener.onLampAvailable(p);

            }
        } catch( JSONException ex) {
            Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
    }
}
