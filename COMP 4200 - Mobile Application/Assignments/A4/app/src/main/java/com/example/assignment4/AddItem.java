package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AddItem extends AppCompatActivity {

    private ArrayList<String> items;
    private Button editItem;
    private EditText input;

    private int changedIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Intent intent = getIntent();
        editItem = findViewById(R.id.editItem);
        input  = findViewById(R.id.input);

        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    addNewItem(view);
                    Intent i = new Intent(AddItem.this, MainActivity.class);
                    startActivity(i);
            }
        });
        Bundle args = intent.getBundleExtra("itemsList");
        items = (ArrayList<String>) args.getSerializable("ARRAYLIST");
//        changedIndex = Integer.parseInt(intent.getStringExtra("changedIndex"));
    }

//    private void changeExistingItem(View view) {
//        items.set(changedIndex, input.getText().toString());
//
//        FileSaver.writeData(items, getBaseContext());
//        input.setText("");
//    }

    private void addNewItem(View view) {

        String itemText = input.getText().toString();

        if(itemText.isEmpty()){
            Snackbar snackbar = Snackbar
                    .make(view, "Cannot add empty item!", Snackbar.LENGTH_LONG);
            snackbar.setAction("x", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Call your action method here
                    snackbar.dismiss();
                }
            });

            snackbar.show();
        }
        else{
            items.add(itemText);
            FileSaver.writeData(items, getBaseContext());
            input.setText("");
        }

    }










//                if(changed){
//                    changeExistingItem(view);
//                    changed = false;
//                }else{




}