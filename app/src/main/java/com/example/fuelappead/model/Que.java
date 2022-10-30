/**
 * IT19123950 Madusanka G.A.P
 * IT19214580 S.M Bulner
 * 26/10/2022
 */
package com.example.fuelappead.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Que {

    @SerializedName("car")
    @Expose
    private String car;

    @SerializedName("van")
    @Expose
    private String van;

    @SerializedName("bus")
    @Expose
    private String bus;

    @SerializedName("total")
    @Expose
    private String total;

    public Que(){
    }

    public Que(String car, String van, String bus, String total) {
        this.car = car;
        this.van = van;
        this.bus = bus;
        this.total = total;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getVan() {
        return van;
    }

    public void setVan(String van) {
        this.van = van;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
