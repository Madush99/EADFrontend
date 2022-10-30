/**
 * IT19123950 Madusanka G.A.P
 * IT19214580 S.M Bulner
 * 26/10/2022
 */
package com.example.fuelappead.remote;
/**
 * Reference
 * https://www.jackrutorial.com/2018/06/retrofit-2-crud-android-example.html
 * https://square.github.io/retrofit/
 */
public class ApiUtills {

    private  ApiUtills(){
    };

    public  static final String API_URL = "http://192.168.1.75:3000/api/";

    /**
     *
     * Gas Station service
     */
    public static FuelService getFuelService(){
        return  RetrofitClient.getClient(API_URL).create(FuelService.class);
    }

    /**
     *
     * User service
     */
    public static UserService getUserService(){
        return  RetrofitClient.getClient(API_URL).create(UserService.class);
    }
}
