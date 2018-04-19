package com.hoangtuan.moduletabhostpagesviews.model;

import java.io.Serializable;

/**
 * Created by atbic on 18/4/2018.
 */

public class NameMainModel implements Serializable{
    String rank;
    String name;
    String gender;
    String origin;
    int popularname;

    public NameMainModel() {
    }

    public NameMainModel(String rank, String name, String gender, String origin, int popularname) {
        this.rank = rank;
        this.name = name;
        this.gender = gender;
        this.origin = origin;
        this.popularname = popularname;
    }

    public NameMainModel(String rank, String name, String gender, String origin) {
        this.rank = rank;
        this.name = name;
        this.gender = gender;
        this.origin = origin;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPopularname() {
        return popularname;
    }

    public void setPopularname(int popularname) {
        this.popularname = popularname;
    }
}
