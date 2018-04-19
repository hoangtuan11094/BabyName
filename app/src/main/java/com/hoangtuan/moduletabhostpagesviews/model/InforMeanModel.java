package com.hoangtuan.moduletabhostpagesviews.model;

import java.io.Serializable;

/**
 * Created by atbic on 18/4/2018.
 */

public class InforMeanModel implements Serializable{
    String mean;
    String origin;
    int lati;
    int longti;

    public InforMeanModel() {
    }

    public InforMeanModel(String mean, String origin, int lati, int longti) {
        this.mean = mean;
        this.origin = origin;
        this.lati = lati;
        this.longti = longti;
    }

    public InforMeanModel(String mean, String origin) {
        this.mean = mean;
        this.origin = origin;
    }

    public int getLati() {
        return lati;
    }

    public void setLati(int lati) {
        this.lati = lati;
    }

    public int getLongti() {
        return longti;
    }

    public void setLongti(int longti) {
        this.longti = longti;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
