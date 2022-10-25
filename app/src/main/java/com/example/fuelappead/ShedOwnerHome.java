package com.example.fuelappead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelappead.model.Fuel;
import com.example.fuelappead.remote.ApiUtills;
import com.example.fuelappead.remote.FuelService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class ShedOwnerHome extends AppCompatActivity {

    private TextView shedname;

    Button btnAddUser;
    Button btnGetUsersList;
    Button btnGetfuelbyshed;
    ListView listView;

    FuelService fuelService;
    List<Fuel> list = new ArrayList<Fuel>();
    List<Fuel> list1 = new ArrayList<Fuel>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shed_owner_home);

        shedname = findViewById(R.id.shedname);
        String username = getIntent().getStringExtra("username");
        shedname.setText(username);

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnGetUsersList = (Button) findViewById(R.id.btnGetUsersList);
        listView = (ListView) findViewById(R.id.listView);
        fuelService = ApiUtills.getFuelService();
        btnGetfuelbyshed = findViewById(R.id.btnGetFuelbyshed);

       btnGetUsersList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               getFuelList();
           }
       });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FuelAdd.class);
                intent.putExtra("username", "");
                intent.putExtra("shedPhone", getIntent().getStringExtra("phoneno"));
                startActivity(intent);
            }
        });

        btnGetfuelbyshed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = getIntent().getStringExtra("phoneno");
                getFuelbyShed(phone);
            }
        });
    }

    public void getFuelList(){
        Call<List<Fuel>> call = fuelService.getFuel();
        call.enqueue(new Callback <List<Fuel>> () {

            @Override
            public void onResponse(Call<List<Fuel>> call, Response<List<Fuel>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new FuelAdapter(ShedOwnerHome.this, R.layout.list_fuel, list));
                    Toast.makeText(ShedOwnerHome.this, "fetched successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Fuel>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        }) ;
    }

    public void getFuelbyShed (String phone){
        Call<List<Fuel>> call = fuelService.getFuelByShed(phone);
        call.enqueue(new Callback <List<Fuel>>(){

            @Override
            public void onResponse(Call<List<Fuel>> call, Response<List<Fuel>> response) {
                if(response.isSuccessful()){
                    list1 = response.body();
                    listView.setAdapter(new FuelAdapter(ShedOwnerHome.this, R.layout.list_fuel, list1));
                    Toast.makeText(ShedOwnerHome.this, "fetched shedby feuwl successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Fuel>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}