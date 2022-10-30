/**
 * IT19123950 Madusanka G.A.P
 * IT19214580 S.M Bulner
 * 26/10/2022
 */
package com.example.fuelappead;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelappead.DBHelper.DBHelper;

public class Login extends AppCompatActivity {

    TextView txtSignUp;
    EditText username, password;
    Button signin;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        signin = findViewById(R.id.signin1);
        txtSignUp = findViewById(R.id.signupText);
        DB = new DBHelper(this);

/**
 * Function for login activity
 */
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(Login.this, "All Fields required", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserpass = DB.usernamepassCheck(user,pass);
                    if(checkuserpass == true){
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Boolean userRoleCheck = DB.userRoleCheck(user, pass);
                        if(userRoleCheck == true){
                            Intent intent = new Intent(getApplicationContext(), ShedOwnerHome.class);

                            intent.putExtra("phoneno", user );
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(getApplicationContext(), UserHome.class);

                            intent.putExtra("phoneno", user );
                            startActivity(intent);
                        }

                    }else {
                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }

}