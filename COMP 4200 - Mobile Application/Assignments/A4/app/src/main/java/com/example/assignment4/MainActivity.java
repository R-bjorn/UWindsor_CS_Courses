package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;

    int changedIndex;
    private FloatingActionButton addNewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        addNewItem = findViewById(R.id.addNewItem);

        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddItem.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)items);
                intent.putExtra("itemsList", args);
                startActivity(intent);
            }
        });

        items = FileSaver.readData(this);
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);
        setUpListViewListener();
    }

    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Context context = getApplicationContext();
//                changedIndex = i;
//
//                Intent intent = new Intent(MainActivity.this, EditItem.class);
//                Bundle args = new Bundle();
//                args.putSerializable("ARRAYLIST",(Serializable)items);
//                intent.putExtra("itemsList", args);
//                intent.putExtra("changedIndex", changedIndex);
//                startActivity(intent);
                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                FileSaver.writeData(items, getBaseContext());
                return true;
            }
        });
    }


}