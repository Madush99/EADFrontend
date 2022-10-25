package com.example.fuelappead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.fuelappead.model.Fuel;
import com.example.fuelappead.remote.ApiUtills;
import com.example.fuelappead.remote.FuelService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class SearchGasStation extends AppCompatActivity {

    EditText locationTxt;
    Button searchlocBtn;
    ListView listView;
    FuelService fuelService;

    List<Fuel> list1 = new ArrayList<Fuel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_gas_station);

        listView = (ListView) findViewById(R.id.btnlistView);
        locationTxt = findViewById(R.id.textLocation);
        searchlocBtn = findViewById(R.id.btnSearchLocation);
        fuelService = ApiUtills.getFuelService();

        searchlocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lco = locationTxt.getText().toString();
                searchShedLocation(lco);
            }
        });
    }

    public void searchShedLocation (String location){

        Call<List<Fuel>> call = fuelService.searchLocation(location);
        call.enqueue(new Callback<List<Fuel>>(){

            @Override
            public void onResponse(Call<List<Fuel>> call, Response<List<Fuel>> response) {
                if(response.isSuccessful()){
                    list1 = response.body();
                    listView.setAdapter(new UserSatationAdapter(SearchGasStation.this, R.layout.fuel_listuser, list1));
                    Toast.makeText(SearchGasStation.this, "fetched shedby feuwl successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Fuel>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}