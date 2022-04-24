package com.example.assignment30;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    private ArrayList<Card> cardList;

    public CardAdapter(ArrayList<Card> list){
        cardList = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView cardName, cardAbout;
        private ImageView cardImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardName = itemView.findViewById(R.id.cardName);
            cardAbout = itemView.findViewById(R.id.cardAbout);
            cardImage = itemView.findViewById(R.id.cardImage);
        }
    }

    @NonNull
    @Override
    public CardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.MyViewHolder holder, int position) {

        Card card = cardList.get(position);

        holder.cardName.setText(card.getCardName());
        if(card.getCardName().equals("Tom & Jerry")){
            holder.cardName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.getContext(), TomJerry.class);
                    MainActivity.getContext().startActivity(intent);
                }
            });
        }
        holder.cardAbout.setText(card.getCardAbout());
        holder.cardImage.setImageResource(card.getCardImage());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
