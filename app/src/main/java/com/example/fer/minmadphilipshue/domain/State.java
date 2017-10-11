package com.example.fer.minmadphilipshue.domain;

/**
 * Created by ferdinand on 11-10-2017.
 */

public class State {
    boolean on;
    int bri;
    int hue;
    int sat;

    public State(boolean on, int bri, int hue, int sat) {
        this.on = on;
        this.bri = bri;
        this.hue = hue;
        this.sat = sat;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
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
