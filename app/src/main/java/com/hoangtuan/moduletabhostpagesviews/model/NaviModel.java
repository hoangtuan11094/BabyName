package com.hoangtuan.moduletabhostpagesviews.model;

/**
 * Created by atbic on 18/4/2018.
 */

public class NaviModel {
    String name;
    int img;

    public NaviModel() {
    }

    public NaviModel(String name, int img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
