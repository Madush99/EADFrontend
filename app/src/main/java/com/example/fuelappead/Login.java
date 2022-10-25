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
//    Spinner dropdown;
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
//        dropdown = findViewById(R.id.spinner2);
//        String[] items = new String[]{"User", "Shed Owner"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.spinner_item1, items);
//        dropdown.setAdapter(adapter);
//        dropdown.setOnItemSelectedListener(new Register.Listener_Of_Selecting_Room_Spinner());

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String role = Listener_Of_Selecting_Room_Spinner.RoomType;
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
                            intent.putExtra("username", role);
                            intent.putExtra("phoneno", user );
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(getApplicationContext(), UserHome.class);
                            intent.putExtra("username", role);
                            intent.putExtra("phoneno", user );
                            startActivity(intent);
                        }
//                        Intent intent = new Intent(getApplicationContext(), UserHome.class);
//                        intent.putExtra("username", user);
//                        startActivity(intent);
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
    public static class Listener_Of_Selecting_Room_Spinner implements AdapterView.OnItemSelectedListener
    {
        static String RoomType;

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id)
        {
            // By using this you can get the position of item which you
            // have selected from the dropdown

            RoomType = (parent.getItemAtPosition(pos)).toString();
        }

        public void onNothingSelected(AdapterView<?> parent)
        {
            // Do nothing.
        }
    };
}