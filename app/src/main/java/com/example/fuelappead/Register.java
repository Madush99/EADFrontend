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
import android.widget.Toast;

import com.example.fuelappead.DBHelper.DBHelper;

public class Register extends AppCompatActivity {

    EditText username,phonenumber,password,confirmpassword;
    Button signup, signin;
    Spinner dropdown;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.username);
        phonenumber=findViewById(R.id.phoneno);
        password=findViewById(R.id.password);
        confirmpassword=findViewById(R.id.confirmpassword);
        signin=findViewById(R.id.signin);
        signup=findViewById(R.id.signup);
        DB = new DBHelper(this);
        dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"User", "Shed Owner"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new Listener_Of_Selecting_Room_Spinner());

        /**
         * onclick function to register
         */
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String phone = phonenumber.getText().toString();
                String role = Listener_Of_Selecting_Room_Spinner.Usertype;
                String pass = password.getText().toString();
                String confirmpass = confirmpassword.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmpass)){
                    Toast.makeText(Register.this, "All Fields required", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(confirmpass)){
                        Boolean checkuser = DB.usernameCheck(user);
                        if(checkuser == false){
                            Boolean insert = DB.RegisterUser(user,phone,role,pass);
                            if(insert == true){
                                Toast.makeText(Register.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
//                                if(role == "User"){
//                                    Intent intent = new Intent(getApplicationContext(), Login.class);
//                                    intent.putExtra("username", role);
//                                    startActivity(intent);
//                                }else {
//                                    Intent intent = new Intent(getApplicationContext(), Login.class);
//                                    intent.putExtra("username", role);
//                                    startActivity(intent);
//                                }
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                    intent.putExtra("username", role);
                                    startActivity(intent);


                            }else{
                                Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(Register.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Register.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Dropdown function for select user type
     */
    public static class Listener_Of_Selecting_Room_Spinner implements AdapterView.OnItemSelectedListener
    {
        static String Usertype;

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id)
        {
            // By using this you can get the position of item which you
            // have selected from the dropdown

            Usertype = (parent.getItemAtPosition(pos)).toString();
        }

        public void onNothingSelected(AdapterView<?> parent)
        {
            // Do nothing.
        }
    };

}