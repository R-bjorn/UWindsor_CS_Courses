package com.example.assignment31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    String user, pwd;
    Button login;
    Switch remember;
    boolean rememberMe;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("Users" , Context.MODE_PRIVATE);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        remember = (Switch) findViewById(R.id.remember);
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                System.out.println("Switch");
                if(isChecked)
                    rememberMe = true;
                else
                    rememberMe = false;
            }
        });
    }

    public void loginUser(){

        user = username.getText().toString().trim();
        pwd = password.getText().toString().trim();

        if(rememberMe){ // if checked on remember
            // save username and password
            SharedPreferences.Editor editor = sp.edit();

            editor.putString("Username" , user);
            editor.putString("Password", pwd);
            editor.commit();

            Toast.makeText(MainActivity.this, "User information saved!", Toast.LENGTH_SHORT).show();

            System.out.println("Save Password");
        }
    }
}