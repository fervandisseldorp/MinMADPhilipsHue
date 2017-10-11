package com.example.fer.minmadphilipshue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ferdinand on 11-10-2017.
 */

public class LampDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lampdetail);

        Bundle extras = getIntent().getExtras();
        int i = extras.getInt("INDEX");
    }

}
