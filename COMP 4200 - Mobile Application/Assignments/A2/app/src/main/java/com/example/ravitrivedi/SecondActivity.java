package com.example.ravitrivedi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String NAME = "NAME";
    public static final String AGE = "AGE";
    public static final String STUDENT = "YES/NO";

    @SuppressLint("SetTextI18n")

    protected void onStart() {
        super.onStart();
        Log.d("Trivedi", "Starting Second Activity");
    }
    protected void onResume() {

        super.onResume();
        Log.d("Trivedi", "Resuming Second Activity");

    }
    protected void onRestart() {
        super.onRestart();
        Log.d("Trivedi", "Restarting Second Activity");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Trivedi", "Destroying Second Activity");
    }
    protected void onPause() {
        super.onPause();
        Log.d("Trivedi", "Pausing Seconds Activity");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Trivedi", "Creating Second Activity");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView nameData = findViewById(R.id.nameData);
        TextView ageData = findViewById(R.id.ageData);
        TextView studentData = findViewById(R.id.studentData);

        Intent data = getIntent();
        String name = data.getStringExtra(NAME);
        String age = data.getStringExtra(AGE);
        boolean currentStudent = data.getBooleanExtra(STUDENT, false);

        nameData.setText(name);
        ageData.setText(age);
        if(currentStudent)
            studentData.setText("Yes");
        else
            studentData.setText("No! Why?");

    }

    public void switchBack(View view) {
        Log.d("Trivedi", "Switching to Main Activity");
        Intent switchBack = new Intent(this,MainActivity.class);
        startActivity(switchBack);
    }
}