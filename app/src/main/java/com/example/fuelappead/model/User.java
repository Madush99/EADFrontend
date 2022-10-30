/**
 * IT19123950 Madusanka G.A.P
 * IT19214580 S.M Bulner
 * 26/10/2022
 */
package com.example.fuelappead.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("shedPhoneNo")
    @Expose
    private String shedPhoneNo;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("userPhoneNo")
    @Expose
    private String userPhoneNo;

    @SerializedName("vehicleType")
    @Expose
    private String vehicleType;

    public User(){

    }

    public User(String id, String shedPhoneNo, String userName, String userPhoneNo, String vehicleType) {
        this.id = id;
        this.shedPhoneNo = shedPhoneNo;
        this.userName = userName;
        this.userPhoneNo = userPhoneNo;
        this.vehicleType = vehicleType;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
