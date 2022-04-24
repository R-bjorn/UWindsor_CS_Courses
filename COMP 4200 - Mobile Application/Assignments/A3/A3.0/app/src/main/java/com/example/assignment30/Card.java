package com.example.assignment30;

import android.widget.ImageView;
import android.widget.TextView;

public class Card {
    private String cardName, cardAbout;
    private int cardImage;


    public Card(String cardName, String cardAbout, int cardImage) {
        this.cardName = cardName;
        this.cardAbout = cardAbout;
        this.cardImage = cardImage;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardAbout() {
        return cardAbout;
    }

    public void setCardAbout(String cardAbout) {
        this.cardAbout = cardAbout;
    }

    public int getCardImage() {
        return cardImage;
    }

    public void setCardImage(int cardImage) {
        this.cardImage = cardImage;
    }
}
