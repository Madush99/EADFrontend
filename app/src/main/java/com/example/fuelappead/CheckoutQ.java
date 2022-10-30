/**
 * IT19123950 Madusanka G.A.P
 * IT19214580 S.M Bulner
 * 26/10/2022
 */
package com.example.fuelappead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fuelappead.model.Fuel;
import com.example.fuelappead.model.User;
import com.example.fuelappead.remote.ApiUtills;
import com.example.fuelappead.remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutQ extends AppCompatActivity {

    ListView listView1;
    Button btnuser;

    UserService userService;
    List<User> list3 = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_q);

        listView1 = findViewById(R.id.listView2);

        userService = ApiUtills.getUserService();

        QueueUserDetails("0766663883");
        getUser();

    }

    /**
     * Get user check In details
     */
    public void getUser(){
     Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    list3 = response.body();
                    listView1.setAdapter(new UserAdapter(CheckoutQ.this, R.layout.list_users, list3));
                    Toast.makeText(CheckoutQ.this, "fetched successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        }) ;
    }

    public void QueueUserDetails (String phone){
        Call<List<User>> call = userService.UserDetailsQueue(phone);
        call.enqueue(new Callback <List<User>>(){

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    list3 = response.body();
                    listView1.setAdapter(new UserAdapter(CheckoutQ.this, R.layout.list_fuel, list3));
                    Toast.makeText(CheckoutQ.this, "fetched shedby feuwl successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}