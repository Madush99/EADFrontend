/**
 * IT19123950 Madusanka G.A.P
 * IT19214580 S.M Bulner
 * 26/10/2022
 */
package com.example.fuelappead.remote;

import com.example.fuelappead.model.Fuel;
import com.example.fuelappead.model.Que;
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
public interface UserService {

    @GET("user/allOrders")
    Call<List<User>> getUsers();

    @POST("user/createOrder/")
    Call<User> QcheckIn1(@Body User user);

    @DELETE("user/delete/{id}")
    Call<User> userCheckout(@Path("id") String id);

    @PUT("user/update/{id}")
    Call<User> updateUser(@Path("id") String id, @Body User user);

    @GET("user/vehicleCount/{phone}")
    Call<List<Que>> queCount(@Path("phone") String phone);

    @GET("user/details/{phone}")
    Call<List<User>> UserDetailsQueue(@Path("phone") String phone);

}
