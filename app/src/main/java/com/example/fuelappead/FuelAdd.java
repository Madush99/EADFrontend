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
import android.widget.EditText;
import android.widget.Toast;

import com.example.fuelappead.model.Fuel;
import com.example.fuelappead.remote.ApiUtills;
import com.example.fuelappead.remote.FuelService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class FuelAdd extends AppCompatActivity {

    FuelService fuelService;
    EditText edtfuelId;
    EditText phoneNumber;
    EditText shedName;
    EditText fuelType;
    EditText fuelStatus;
    EditText stationLocation;
    Button btnSave;
    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_add);

        //edtfuelId = findViewById(R.id.shedId);
        phoneNumber = findViewById(R.id.shedPhone);
        shedName = findViewById(R.id.name);
        fuelType = findViewById(R.id.fuelType);
        fuelStatus = findViewById(R.id.fuelStatus);
        btnSave = findViewById(R.id.btnSave);
        btnDel = findViewById(R.id.btnDel);
        stationLocation = findViewById(R.id.satloc);
        fuelService = ApiUtills.getFuelService();

        Bundle extras = getIntent().getExtras();
        final String fuelId = extras.getString("fuel_id");
        String shedPhone = extras.getString("shed_Phone");
        String shed_Name = extras.getString("shed_name");
        String fuel_Type = extras.getString("fuel_type");
        String fuel_Status = extras.getString("fuel_status");
        String sat_loc = extras.getString("station_location");

        //edtfuelId.setText(fuelId);
        if(fuelId != null){
            phoneNumber.setText(shedPhone);
        }else{
            phoneNumber.setText(getIntent().getStringExtra("shedPhone"));
        }

        shedName.setText(shed_Name);
        fuelType.setText(fuel_Type);
        fuelStatus.setText(fuel_Status);
        stationLocation.setText(sat_loc);

        /**
         * onClick function to save
         */
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fuel f = new Fuel();
              //  f.setId(edtfuelId.getText().toString());
                f.setShedPhoneNo(phoneNumber.getText().toString());
                f.setShedName(shedName.getText().toString());
                f.setFuelType(fuelType.getText().toString());
                f.setFuelStatus(fuelStatus.getText().toString());
                f.setShedLocation(stationLocation.getText().toString());

                if(fuelId != null && fuelId.trim().length()>0){
                    updateFuelStatus(fuelId, f);
                    Intent intent = new Intent(getApplicationContext(),ShedOwnerHome.class);
                    startActivity(intent);
                }else {
                    addFuel(f);
                    Intent intent = new Intent(getApplicationContext(),ShedOwnerHome.class);
                    startActivity(intent);
                }


            }
        });
/**
 * onclick function delete
 */
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fuelDelete(fuelId);

                Intent intent = new Intent(getApplicationContext(),ShedOwnerHome.class);
                startActivity(intent);

            }
        });
    }

    /**
     *
     * Add Fuel Station
     */
    public void addFuel(Fuel f){
        Call<Fuel> call = fuelService.addFuel(f);
        call.enqueue(new Callback<Fuel>() {
            @Override
            public void onResponse(Call<Fuel> call, Response<Fuel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(FuelAdd.this, "Fuel Added successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Fuel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     *
     *Update Station details
     */
    public void updateFuelStatus(String id, Fuel f){

        Call<Fuel> call = fuelService.updateFuel(id,f);
        call.enqueue(new Callback<Fuel>() {
            @Override
            public void onResponse(Call<Fuel> call, Response<Fuel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(FuelAdd.this, "Fuel Updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Fuel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     *
     * Delete Station details
     */
    public void fuelDelete (String id){
        Call<Fuel> call = fuelService.deleteFuel(id);
        call.enqueue(new Callback<Fuel>() {
            @Override
            public void onResponse(Call<Fuel> call, Response<Fuel> response) {
                if(response.isSuccessful()){

                    Toast.makeText(FuelAdd.this, "Fuel Deleted Successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Fuel> call, Throwable t) {

            }
        });
    }
}