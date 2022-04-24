package com.example.a5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Division extends AppCompatActivity {

    TextView question;
    EditText input;
    Button submit, next;
    String newQuestion = "What is 6 + 3";
    String digit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        question = findViewById(R.id.question);

        input = findViewById(R.id.input);
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                digit = input.getText().toString();

                return false;
            }
        });
    }
}