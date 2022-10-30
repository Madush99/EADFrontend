/**
 * IT19123950 Madusanka G.A.P
 * IT19214580 S.M Bulner
 * 26/10/2022
 */
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

    ListView listView;

    FuelService fuelService;
    List<Fuel> list = new ArrayList<Fuel>();
    List<Fuel> list1 = new ArrayList<Fuel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shed_owner_home);


        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        listView = (ListView) findViewById(R.id.listView);
        fuelService = ApiUtills.getFuelService();


        String phone = getIntent().getStringExtra("phoneno");
        getFuelbyShed(phone);


        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FuelAdd.class);
                intent.putExtra("username", "");
                intent.putExtra("shedPhone", getIntent().getStringExtra("phoneno"));
                startActivity(intent);
            }
        });

    }

    /**
     *
     * get the relevant gas station details of the owner
     */
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