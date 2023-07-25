package com.someName.projektarbete;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scan.next();

        Deck deckTemplate = new Deck();
        Participant player1 = new Participant(name);
        Dealer dealer = new Dealer();

        List<Card> deckOfCards = deckTemplate.generateDeck();   // Sorted by default

        Menu menu = new Menu(player1, dealer, deckOfCards);

        menu.mainMenu(); // Starts Blackjack

    }
}
