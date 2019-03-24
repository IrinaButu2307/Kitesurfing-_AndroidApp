package com.example.kitesurfingapp.models;

public class Idea {
    private int id;
    private String country;
    private String latitude;
    private String longitude;
    private String windProbability;
    private String whereToGo;

    public Idea() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongiyude(String longiyude) {
        this.longitude = longiyude;
    }

    public String getWindProbability() {
        return windProbability;
    }

    public void setWindProbability(String windProbability) {
        this.windProbability = windProbability;
    }

    public String getWhereToGo() {
        return whereToGo;
    }

    public void setWhereToGo(String whereToGo) {
        this.whereToGo = whereToGo;
    }
}
