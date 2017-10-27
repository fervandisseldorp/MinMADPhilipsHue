package com.example.fer.minmadphilipshue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fer.minmadphilipshue.domain.PhilipsHueLamp;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnLampAvailable {
    ArrayList<PhilipsHueLamp> lampArray = new ArrayList<>();
    ArrayAdapter<PhilipsHueLamp> adapter;
    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set Layout and layoutcomponents
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);

        getAllLamps();

        // Set adapter for listview
        adapter = new PhilipsHueAdapter(getApplicationContext(), lampArray );
        listview.setAdapter(adapter);
        listview.setOnItemClickListener((AdapterView.OnItemClickListener) this);


    }

    @Override
    public void onLampAvailable(PhilipsHueLamp philipsHueLamp) {
        lampArray.add(philipsHueLamp);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getApplicationContext(), LampDetailActivity.class);

        PhilipsHueLamp p = lampArray.get(i);
        intent.putExtra("LAMP", p );

        startActivity(intent);
    }


    public void getAllLamps(){
        String[] url = new String[] { "http://192.168.1.179/api/lXPypsuuj4ujhAlXdZP2URMJpVZwqUS-HAMrojC0/lights"};
        AsyncActivity asyncActivity = new AsyncActivity(this);
        String[] urlParam;
        asyncActivity.execute(url);

    }
}
