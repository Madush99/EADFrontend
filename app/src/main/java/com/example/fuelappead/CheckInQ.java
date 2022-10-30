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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelappead.model.Que;
import com.example.fuelappead.model.User;
import com.example.fuelappead.remote.ApiUtills;
import com.example.fuelappead.remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInQ extends AppCompatActivity {

    UserService userService;
    EditText edtShedPhone;
    EditText edtUseranme;
    EditText edtUserPhone;
    EditText edtvehType;
    TextView vehCount;
    TextView queTime;
    ListView listView;


    Button btnAddUser;
    Button btncheckout;
    Button btncheckout2;

    List<Que> list = new ArrayList<Que>();

    public int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_q);

        edtShedPhone = findViewById(R.id.satPhone);
        edtUseranme = findViewById(R.id.edtuserName);
        edtUserPhone = findViewById(R.id.userPhone);
        edtvehType  = findViewById(R.id.vehtype);
        vehCount = findViewById(R.id.countcar);
        queTime = findViewById(R.id.timeq);
        btnAddUser =findViewById(R.id.btnSaveq);
        btncheckout = findViewById(R.id.btncheckout1);
        btncheckout2 = findViewById(R.id.btncheckout2);
        listView = (ListView) findViewById(R.id.listView);

        userService = ApiUtills.getUserService();

        Bundle extras = getIntent().getExtras();
        final String user_id = extras.getString("user_id");
        String shed_phone = extras.getString("shed_user_phone");
        String user_name = extras.getString("user_name");
        String user_phone = extras.getString("user_phone");
        String veh_type = extras.getString("veh_type");

        edtShedPhone.setText(shed_phone);
        edtUseranme.setText(user_name);
        if(user_id != null){
            edtUserPhone.setText(user_phone);
        }else{
            edtShedPhone.setText(getIntent().getStringExtra("shed_phone"));
        }
        edtvehType.setText(veh_type);
        queCount(getIntent().getStringExtra("shed_phone"));

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u = new User();
                u.setShedPhoneNo(edtShedPhone.getText().toString());
                u.setUserName(edtUseranme.getText().toString());
                u.setUserPhoneNo(edtUserPhone.getText().toString());
                u.setVehicleType(edtvehType.getText().toString());

                if(user_id != null && user_id.trim().length() > 0){
                    updateCheckoutStatus(user_id, u);
                    Intent intent = new Intent(getApplicationContext(), UserHome.class);
                    startActivity(intent);
                }else {
                    logInheck(u);
                    Intent intent = new Intent(getApplicationContext(), UserHome.class);
                    startActivity(intent);
                }

            }
        });

        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCheckout(user_id);
            }
        });
        btncheckout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCheckout(user_id);
            }
        });

    }

    /**
     *
     *User Check in to the queue function
     */
    public void logInheck(User u){
        Call<User> call = userService.QcheckIn1(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CheckInQ.this, "User Enter In successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     *
     * User exit from the queue function
     */
    public void userCheckout (String id){
        Call<User> call = userService.userCheckout(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){

                    Toast.makeText(CheckInQ.this, "User exit successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    /**
     *
     * Update user
     */
    public void updateCheckoutStatus(String id, User u){

        Call<User> call = userService.updateUser(id,u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CheckInQ.this, "Updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     *
     *Get the queue count
     */
    public void queCount (String phone){

        Call<List<Que>> call = userService.queCount(phone);
        call.enqueue(new Callback<List<Que>>(){

            @Override
            public void onResponse(Call<List<Que>> call, Response<List<Que>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    size = list.size();
                    System.out.println(size * 5);
                    vehCount.setText(String.format("Vehicles In The Queue: %s",String.valueOf(size)));
                    queTime.setText(String.format("Waiting Time: %s min", String.valueOf(size* 5)));
                    Toast.makeText(CheckInQ.this, "fetched  successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Que>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }


}