package com.example.fer.minmadphilipshue.domain;

import java.io.Serializable;

/**
 * Created by ferdinand on 11-10-2017.
 */

public class PhilipsHueLamp implements Serializable{
    private State state;
    private String type;
    private String name;
    private String modelid;
    private String swversion;
    private String uniqueid;
    // private String pointsymbol;


    public PhilipsHueLamp(){

    }

    public PhilipsHueLamp(State state, String type, String name, String modelid, String swversion, String uniqueid) {
        this.state = state;
        this.type = type;
        this.name = name;
        this.modelid = modelid;
        this.swversion = swversion;
        this.uniqueid = uniqueid;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public String getSwversion() {
        return swversion;
    }

    public void setSwversion(String swversion) {
        this.swversion = swversion;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }
}
