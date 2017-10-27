package com.example.fer.minmadphilipshue;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fer.minmadphilipshue.domain.PhilipsHueLamp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * Created by ferdinand on 26-10-2017.
 */

public class AsyncPut extends AsyncTask<String, Void, String> {

    //private OnLampAvailable listener;
    //private String getUrl = "http://192.168.1.179/api/lXPypsuuj4ujhAlXdZP2URMJpVZwqUS-HAMrojC0/lights";
    private PhilipsHueLamp philipsHueLamp;

    public AsyncPut(PhilipsHueLamp philipsHueLamp){
        this.philipsHueLamp = philipsHueLamp;
    }

    @Override
    protected String doInBackground(String... params) {
        System.out.println("reached do in background");
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("on", philipsHueLamp.isOn() );
            jsonObject.put("bri", philipsHueLamp.getBri());
            jsonObject.put("hue", philipsHueLamp.getHue());
            jsonObject.put("sat", philipsHueLamp.getSat() );


            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.connect();

            DataOutputStream output = new DataOutputStream( connection.getOutputStream() );
            output.writeBytes( jsonObject.toString() );

            BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream() ));
            System.out.println(reader.readLine());

            output.close();
            connection.disconnect();

        } catch( JSONException ex) {
            Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
