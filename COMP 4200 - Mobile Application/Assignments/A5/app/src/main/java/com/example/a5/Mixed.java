package com.example.a5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Mixed extends AppCompatActivity {

    TextView question;
    EditText input;
    Button submit, next;
    String newQuestion = "What is 6 + 3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixed);

        question.setText(getMixedQuestion());
    }

    private String getMixedQuestion() {
        String que = "";

        que = (Math.random() * 100) + " + " + (Math.random() * 100);
        return que;
    }
}