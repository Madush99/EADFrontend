/**
 * IT19123950 Madusanka G.A.P
 * IT19214580 S.M Bulner
 * 26/10/2022
 */
package com.example.fuelappead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserHome extends AppCompatActivity {

    private TextView name;
    private Button searchBtn;
//    private Button btnQ;
    private Button btnchckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        name = findViewById(R.id.name);
        searchBtn = findViewById(R.id.btnSearch);
        btnchckOut = findViewById(R.id.btncheckout);
        String username = getIntent().getStringExtra("username");

        name.setText(username);
/**
 * onClick function to search page
 */
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SearchGasStation.class);
                startActivity(intent);
            }
        });

/**
 * Onclick function to exit from queue page
 */
        btnchckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckoutQ.class);
                startActivity(intent);
            }
        });
    }
}