package com.example.fer.minmadphilipshue.domain;

import java.io.Serializable;

/**
 * Created by ferdinand on 11-10-2017.
 */

public class PhilipsHueLamp implements Serializable{
    private String id;
    private boolean isOn;
    private int bri;
    private int hue;
    private int sat;


    public PhilipsHueLamp(String id, boolean isOn, int bri, int hue, int sat, String name){
        this.id = id;
        this.isOn = isOn;
        this.bri = bri;
        this.hue = hue;
        this.sat = sat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public int getBri() {
        return bri;
    }

    public void setBri(int bri) {
        this.bri = bri;
    }

    public int getHue() {
        return hue;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }
}
