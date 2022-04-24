package com.example.assignment30;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Card> cardArrayList;
    private RecyclerView recyclerView;

    public static Context getContext() {
        return getContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        cardArrayList = new ArrayList<>();
        addCards();
        addCardAdapter();
    }

    private void addCards(){
        cardArrayList.add(new Card("Squid Game", "South Korean survival drama series" , R.drawable.image1));
        cardArrayList.add(new Card("My Hero Academia" , "Japanese superhero manga series" , R.drawable.image2));
        cardArrayList.add(new Card("Tom & Jerry", "American animated media series", R.drawable.image3));
    }

    private void addCardAdapter(){
        CardAdapter adapter = new CardAdapter(cardArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 745));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}