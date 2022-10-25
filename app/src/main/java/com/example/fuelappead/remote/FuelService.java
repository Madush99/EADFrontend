package com.example.fuelappead.remote;

import com.example.fuelappead.model.Fuel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FuelService {
    @GET("allFuel/")
    Call<List<Fuel>> getFuel();

    @POST("createFuel/")
    Call<Fuel> addFuel(@Body Fuel fuel);

    @GET("shed/{phone}")
    Call<List<Fuel>> getFuelByShed(@Path("phone") String phone);

    @PUT("update/{id}")
    Call<Fuel> updateFuel(@Path("id") String id, @Body Fuel fuel);

    @DELETE("delete/{id}")
    Call<Fuel> deleteFuel(@Path("id") String id);

    @GET("location/{location}")
    Call<List<Fuel>> searchLocation(@Path("location") String location);
}
