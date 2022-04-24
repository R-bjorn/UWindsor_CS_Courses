package com.example.a5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Substraction extends AppCompatActivity {

    TextView question;
    EditText input;
    Button submit, next;
    String newQuestion = "What is 6 + 3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substraction);

        question.setText(subtractionQuestion());
    }

    private String subtractionQuestion() {
        String que = "";
        int num1 = (int) Math.random() * 100;
        int num2 = (int) Math.random() * 100;

        que = num2 + " - " + num1;

        return que;
    }
}