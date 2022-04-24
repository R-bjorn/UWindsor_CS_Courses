package com.example.a5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Addition extends AppCompatActivity {

    TextView question;
    EditText input;
    Button submit, next;
    String newQuestion = "What is 6 + 3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        question = findViewById(R.id.question);
        question.setText(newQuestion);
    }

    private void nextQuestion() {
        // get next question
    }

    private void submitAnswer() {
        // check question
    }
}