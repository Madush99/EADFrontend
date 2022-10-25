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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        name = findViewById(R.id.name);
        searchBtn = findViewById(R.id.btnSearch);

        String username = getIntent().getStringExtra("username");

        name.setText(username);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SearchGasStation.class);
                startActivity(intent);
            }
        });
    }
}