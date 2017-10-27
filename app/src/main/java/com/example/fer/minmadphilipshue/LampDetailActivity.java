package com.example.fer.minmadphilipshue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import com.example.fer.minmadphilipshue.domain.PhilipsHueLamp;

/**
 * Created by ferdinand on 11-10-2017.
 */

public class LampDetailActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private PhilipsHueLamp philipsHueLamp;
    private AsyncPut asyncPut;
    private SeekBar briBar;
    private SeekBar hueBar;
    private SeekBar satBar;
    private Switch lampSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lampdetail);

        philipsHueLamp = (PhilipsHueLamp) getIntent().getSerializableExtra("LAMP");

        // UI ELEMENTS
        briBar = (SeekBar) findViewById(R.id.seekBarBri);
        hueBar = (SeekBar) findViewById(R.id.seekBarHue);
        satBar = (SeekBar) findViewById(R.id.seekBarSat);
        lampSwitch = (Switch) findViewById(R.id.lampSwitch);

        // MAX values
        briBar.setMax(255);
        hueBar.setMax(50000);
        satBar.setMax(254);

        System.out.println("lamp status : " + philipsHueLamp.isOn() );
        briBar.setProgress( philipsHueLamp.getBri() );
        hueBar.setProgress( philipsHueLamp.getHue() );
        satBar.setProgress( philipsHueLamp.getSat() );
        lampSwitch.setChecked( philipsHueLamp.isOn() );

        briBar.setOnSeekBarChangeListener(this);
        hueBar.setOnSeekBarChangeListener(this);
        satBar.setOnSeekBarChangeListener(this);

        lampSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                philipsHueLamp.setOn(b);
                sendRequest();
            }
        });

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch(seekBar.getId() ){
            case R.id.seekBarBri:
                philipsHueLamp.setBri( briBar.getProgress() );
                sendRequest();
                break;
            case R.id.seekBarHue:
                philipsHueLamp.setHue( hueBar.getProgress() );
                sendRequest();
                break;
            case R.id.seekBarSat:
                philipsHueLamp.setSat( satBar.getProgress() );
                sendRequest();
                break;
            default:
                break;
        }
    }

    public void sendRequest(){
        String[] url = new String[] {"http://192.168.1.179/api/lXPypsuuj4ujhAlXdZP2URMJpVZwqUS-HAMrojC0/lights/" + philipsHueLamp.getId() +"/state"};
        System.out.println("values: " + philipsHueLamp.getBri() + " " + philipsHueLamp.getHue() + " " + philipsHueLamp.getSat());
        asyncPut = new AsyncPut(philipsHueLamp);
        asyncPut.execute(url);
    }
}
