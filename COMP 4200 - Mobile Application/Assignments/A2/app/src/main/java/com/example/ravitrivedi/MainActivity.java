package com.example.ravitrivedi;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity{

    public EditText number;
    public Button click;
    public Button reset;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = (Button) findViewById(R.id.reset);
        reset.setVisibility(View.INVISIBLE);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("RESET!");
                builder.setMessage("Do you want to reset the value ? ").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetNumber();
                    }
                }).setNegativeButton("No",null);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        click = (Button) findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseNumber();
                reset.setVisibility(View.VISIBLE);
            }
        });

        number = (EditText) findViewById(R.id.number);

    }

    public void resetNumber(){
        number.setText("0");
    }


    public void increaseNumber(){
        int num = Integer.parseInt(number.getText().toString().trim());
        num++;
        number.setText(String.valueOf(num));
    }

}