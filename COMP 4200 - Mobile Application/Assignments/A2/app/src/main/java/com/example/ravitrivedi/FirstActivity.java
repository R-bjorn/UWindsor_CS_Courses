package com.example.ravitrivedi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class FirstActivity extends AppCompatActivity{

    private EditText getName, getAge;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch getStudent;

    protected void onStart() {
        super.onStart();
        Log.d("Trivedi", "Starting Main Activity");
    }
    protected void onResume() {

        super.onResume();
        Log.d("Trivedi", "Resuming Main Activity");

    }
    protected void onRestart() {
        super.onRestart();
        Log.d("Trivedi", "Restarting Main Activity");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Trivedi", "Destroying Main Activity");
    }
    protected void onPause() {
        super.onPause();
        Log.d("Trivedi", "Pausing Main Activity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Trivedi", "Creating Main Activity");

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        getName = (EditText) findViewById(R.id.getName);
//        getName.setOnClickListener(view -> getName.getText().clear());
//
//        getAge = (EditText) findViewById(R.id.getAge);
//        getAge.setOnClickListener(view -> getAge.getText().clear());
//
//        getStudent = (Switch) findViewById(R.id.uniStudent);
//
//        Button switchButton = (Button) findViewById(R.id.switchSecond);
//        switchButton.setOnClickListener(view -> switchSecond());
    }

    public void switchSecond(){
        Log.d("Trivedi", "Switching to Second Activity");
        String name = getName.getText().toString().trim();
        String age = getAge.getText().toString().trim();
        boolean currentStudent = getStudent.isChecked();
        Intent switchToSecond = new Intent(FirstActivity.this,SecondActivity.class);

        switchToSecond.putExtra(SecondActivity.NAME, name);
        switchToSecond.putExtra(SecondActivity.AGE, age);
        switchToSecond.putExtra(SecondActivity.STUDENT, currentStudent);


        startActivity(switchToSecond);
    }
}