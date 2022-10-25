package com.example.fuelappead.remote;

public class ApiUtills {

    private  ApiUtills(){
    };

    public  static final String API_URL = "http://192.168.1.75:3000/api/fuel/";

    public static FuelService getFuelService(){
        return  RetrofitClient.getClient(API_URL).create(FuelService.class);
    }
}
