/**
 * IT19123950 Madusanka G.A.P
 * IT19214580 S.M Bulner
 * 26/10/2022
 */
package com.example.fuelappead.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fuel {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("shedPhoneNo")
    @Expose
    private String shedPhoneNo;

    @SerializedName("shedName")
    @Expose
    private String shedName;

    @SerializedName("fuelType")
    @Expose
    private String fuelType;

    @SerializedName("fuelStatus")
    @Expose
    private String fuelStatus;


    @SerializedName("shedLocation")
    @Expose
    private String shedLocation;

    public Fuel() {
    }

    public Fuel(String id, String shedPhoneNo, String shedName, String fuelType, String fuelStatus, String shedLocation) {
        this.id = id;
        this.shedPhoneNo = shedPhoneNo;
        this.shedName = shedName;
        this.fuelType = fuelType;
        this.fuelStatus = fuelStatus;
        this.shedLocation = shedLocation;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getShedPhoneNo() {
        return shedPhoneNo;
    }

    public void setShedPhoneNo(String shedPhoneNo) {
        this.shedPhoneNo = shedPhoneNo;
    }

    public String getShedName() {
        return shedName;
    }

    public void setShedName(String shedName) {
        this.shedName = shedName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelStatus() {
        return fuelStatus;
    }

    public void setFuelStatus(String fuelStatus) {
        this.fuelStatus = fuelStatus;
    }

    public String getShedLocation() {
        return shedLocation;
    }

    public void setShedLocation(String shedLocation) {
        this.shedLocation = shedLocation;
    }
}
