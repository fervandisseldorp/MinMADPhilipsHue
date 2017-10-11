package com.example.fer.minmadphilipshue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fer.minmadphilipshue.domain.PhilipsHueLamp;
import com.example.fer.minmadphilipshue.domain.State;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnLampAvailable {
    ArrayList<PhilipsHueLamp> lampArray = new ArrayList<>();
    ArrayAdapter<PhilipsHueLamp> adapter;
    ListView listview;
    //String username = "iYrmsQq1wu5FxF9CPqpJCnm1GpPVylKBWDUsNDhB";
    //String ipAdress = "145.48.205.33";
    String ipAdress = "http://192.168.1.179/api";
    String user = "lXPypsuuj4ujhAlXdZP2URMJpVZwqUS-HAMrojC0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set Layout and layoutcomponents
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);

        // Set adapter for listview
        adapter = new PhilipsHueAdapter(getApplicationContext(), lampArray);
        listview.setOnItemClickListener((AdapterView.OnItemClickListener) this);
//        State s = new State(false, 100, 100, 100);
//        PhilipsHueLamp ph = new PhilipsHueLamp(s, "testtype", "testname", "modelid", "iswversion", "uniqueid");
        fillLampArray();
    }



    @Override
    public void onLampAvailable(PhilipsHueLamp philipsHueLamp) {
        lampArray.add(philipsHueLamp);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("Clicked on button");
        Intent intent = new Intent(getApplicationContext(), LampDetailActivity.class);

        PhilipsHueLamp p = lampArray.get(i);
        intent.putExtra("LAMP_OBJECT", p );

        startActivity(intent);
    }


    void fillLampArray(){
        lampArray = getAllLamps();
    }

    ArrayList<PhilipsHueLamp> getAllLamps(){
        String[] url = new String[] { "http://192.168.1.179/api"};
        AsyncActivity asyncActivity = new AsyncActivity(this);
        String[] urlParam;
        asyncActivity.execute(url);

        return null;
    }
}
