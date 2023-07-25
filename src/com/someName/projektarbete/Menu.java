package com.someName.projektarbete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    Player player;              // <-- NULL
    Dealer dealer;              // <-- NULL
    List<Card> deckOfCards;     // <-- NULL
    List<Player> listOfPlayers = new ArrayList<>();

    // Constructor
    public Menu(Participant player, Dealer dealer , List<Card> deckOfCards) {  // Konstruktor meny har , player, dealer
        this.player = player;           // <-- player is no longer NULL        //  this = pekare till instans variablerna
        this.dealer = dealer;
        this.deckOfCards = deckOfCards; // <-- deckOfCards is no longer NULL

        listOfPlayers.add(dealer);    // dealer på plats (0)
        listOfPlayers.add(player);    // player på plats (1)
    }



    public void mainMenu() {

        boolean playing = true;

        do {
            System.out.println("""
                    1 - Start game \s
                    2 - Your Profil \s
                    0 - Exit Game \s
                    """);

            switch (scanner.next()) {
                case "1" -> menuStartGame((Participant) player);
                case "2" -> printName((Participant) player); // casta om till subklass  parentes används för prioritering
                                                              //  (Participant) prioriteras före pappan player.
                case "0" -> playing = false;                //  för den är ärvd

                default -> System.out.println("Error");
            }

        } while (playing);

    }


    public void printName(Participant player){            // hämtar namnet, måste ange Argument vart den ska hämta
        System.out.println("Name: " + player.getName());    //
        System.out.println("Money: " + player.getMoney());
    }




    public void menuStartGame(Participant player) {  // Vart metoden ska kallas på fr (Classen Participant player)
                                                      // Sub klassen
        // TODO - Initial 'bet'
        if(player.getMoney() > 0){     // spelar tills han har pengar

            System.out.print("Please lay your bet. Limit  2 - 500 dollar ");
             System.out.println();
            int bet = scanner.nextInt();

            if( player.layBet(bet)){    // Kollar att bet ska vara  mellan 2 - 500


                // TODO - Shuffle
                Collections.shuffle(deckOfCards);

                 // TODO - Receive 2 cards initially
                 for (int i = listOfPlayers.size() - 1; i > 0; i--) {
                     listOfPlayers.get(i).draw(deckOfCards);
                     listOfPlayers.get(i).draw(deckOfCards);

                 }

                /*
                for (int j = 0; j < 2; j++) {
                System.out.println(j);
                 }

                 */

                System.out.println("YOUR CARDS:  " + player.getHand());   // Printar spelarens korten
                System.out.println("YOUR VALUE:  " + player.cardsValue()); // Visar värdet på korten


                System.out.println("Syns dealerns kort");
                dealer.draw(deckOfCards);
                System.out.println("Dealers cards: " + listOfPlayers.get(0).getHand());
                System.out.println("Dealers value: " + listOfPlayers.get(0).cardsValue());



                     // TODO - DO WHILE LOOP
                     // TODO - LOGIC     if > 21
                     boolean isPlaying = true;

                     do {
                         System.out.println();
                         System.out.println("(1) - HIT\n" +
                                            "(2) - STAND");
                         switch (scanner.next()) {
                             case "1" -> {

                                 // TODO - Draw card
                                 player.draw(deckOfCards);            // spelaren drar kort igen
                                 System.out.println("YOUR CARDS: " + player.getHand());   //  korten  visas
                                 System.out.println("YOUR VALUE: " + player.cardsValue()); // värdet visas




                                 // TODO - IF 21
                                 if (player.cardsValue() == 21) {
                                     isPlaying = false;
                                     player.win(bet);
                                 }

                                 // TODO - Check AFTER receiving card if sum > 21
                                 else if (player.cardsValue() > 21) {
                                     isPlaying = false;
                                     winLoose(listOfPlayers, player, bet);

                                 }
                             }


                             // TODO - BONUS (VG) check ACE value


                             case "2" -> {
                                 System.out.println("Stand");
                                 // TODO - Dealer draws card

                                 while (listOfPlayers.get(0).cardsValue() < 17) {   // så länge dealers kort är under 17, ska han dra
                                    listOfPlayers.get(0).draw(deckOfCards);

                                 }

                                 System.out.println("Dealer hand: " + listOfPlayers.get(0).getHand());  // printar ut dealerns korten
                                 System.out.println("Dealers value: " + listOfPlayers.get(0).cardsValue()); // visar värdet på dealerns kort



                                 // TODO - Check if won / lose

                                 winLoose(listOfPlayers, player, bet);
                                 throwCards(listOfPlayers);
                                 isPlaying = false; // spelet avslutas

                             }


                         }
                     }   // Korten kastas fr handen
                     while (isPlaying);
                     throwCards(listOfPlayers);
                 }

        }
        else  {
            System.out.println("Sorry no money left");
            System.exit(0);
        }


    }



    public void throwCards(List<Player> players){    //  lista med spelaren som ska rensa bort kort i handen
        for (Player player  : players){  //   För alla spelare i listan player(sparas i variabeln player)
            player.clearHand();          //  funktionen kallas. rensa handen

        }

    }



    // Argument , behöver Lista av spelarna (dealern , )
    public void  winLoose(List<Player> listOfPlayers, Participant player, int bet ){

        if(listOfPlayers.get(0).cardsValue()> 21 ) {
            System.out.println("Dealer lost");
            player.win(bet);
        }


        else if(listOfPlayers.get(1).cardsValue()> 21) {
            System.out.println("Player lost");}  // pengar försvinner när han förlorar

        else if(listOfPlayers.get(0).cardsValue()> player.cardsValue()) {
            System.out.println("Dealer wins");} //pengar försvinner


        else if (listOfPlayers.get(1).cardsValue() > listOfPlayers.get(0).cardsValue()) {
            System.out.println("Player wins");
            player.win(bet);
        }


        else if (listOfPlayers.get(1).cardsValue() == listOfPlayers.get(0).cardsValue()) {
            System.out.println("Draw");
            player.equls(bet);
        }




    }


}
