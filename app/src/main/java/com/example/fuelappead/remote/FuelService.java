/**
 * IT19123950 Madusanka G.A.P
 * IT19214580 S.M Bulner
 * 26/10/2022
 */
package com.example.fuelappead.remote;

import com.example.fuelappead.model.Fuel;
import com.example.fuelappead.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
/**
 * Reference
 * https://www.jackrutorial.com/2018/06/retrofit-2-crud-android-example.html
 * https://square.github.io/retrofit/
 */
public interface FuelService {
    @GET("fuel/allFuel/")
    Call<List<Fuel>> getFuel();

    @POST("fuel/createFuel/")
    Call<Fuel> addFuel(@Body Fuel fuel);

    @GET("fuel/shed/{phone}")
    Call<List<Fuel>> getFuelByShed(@Path("phone") String phone);

    @PUT("fuel/update/{id}")
    Call<Fuel> updateFuel(@Path("id") String id, @Body Fuel fuel);

    @DELETE("fuel/delete/{id}")
    Call<Fuel> deleteFuel(@Path("id") String id);

    @GET("fuel/location/{location}")
    Call<List<Fuel>> searchLocation(@Path("location") String location);

//    @POST("user/createOrder/")
//    Call<User> QcheckIn(@Body User user);

}
