package com.example.admin.chebbychallenge.data.model;

public class ClimaCity {

    String city;
    double maxTemp;
    double minTemp;
    public String state;
    String iconLink;


    public ClimaCity(String city, double maxTemp, double minTemp, String state, String iconLink) {
        this.city = city;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.state = state;
        this.iconLink = iconLink;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }


}
