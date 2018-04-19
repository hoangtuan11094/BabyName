package com.hoangtuan.moduletabhostpagesviews.model;

/**
 * Created by atbic on 18/4/2018.
 */

public class InforPopularityModel {
    String year;
    String user;
    String rank;

    public InforPopularityModel() {
    }

    public InforPopularityModel(String year, String user, String rank) {
        this.year = year;
        this.user = user;
        this.rank = rank;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
