package com.someName.projektarbete;

import java.util.ArrayList;
import java.util.List;

public class Player {

   private List<Card> hand = new ArrayList<>(); // private håller kortlistan hemlig/osynlig

    public List<Card> getHand() {
        return hand;
    }  // eftersom det är private, hämtas handen med get

    public void draw(List<Card> deckOfCards) {     // drar kort Vart ifrån? fr  Kortlistan deckOfCards
        hand.add(deckOfCards.get(0));
        // pHand.add(deckOfCards.get(deckOfCards.size() -1));  // Drar fr det understa kortet fr kortleken
        deckOfCards.remove(0);
    }

    public int cardsValue(){   // Ska räkna ut kortens värden
        int cardValue = 0;
        for (Card card : hand) {           // för alla korten i handen
            cardValue += card.getCardValue();
        }

        return cardValue;        //retunerar kortens summa
    }



    public void clearHand(){        // Funktion som ska rensa kort med clear
        hand.clear();
    }




}
