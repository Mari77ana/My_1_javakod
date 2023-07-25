package com.someName.projektarbete;

public class Participant extends Player {               //participant = deltagare
    // Pappan (Superklass) Player                      // deltagare ärver från huvudklassen , därför EXTENDS
                                                      // går inte då att skapa nya objekt av classen Player
    // Participant  är barnet. Ärver fr pappan

    private final String name;
    private int money;

    public Participant(String name){
        super();  //  Kallar på pappan, klassen är ärvd
        this.name = name;
        this.money = 500;  // Max gräns 500 dollar
    }

    public String getName() {
        return name;
    }       // private: hämtar namnet

    /*
    public void setName(String name) {
        this.name = name;
    }

     */

    public int getMoney() {
        return money;
    }        // privat: hämtar pengar

    /*
   public void setMoney(int money) {
        this.money = money;
    }

     */

    public boolean layBet(int bet){                // metoden returnerar
        if (bet < 2 || bet > money)  {
            System.out.println(" Bet from 2 - " + money + " dollar");  // begränsning  2 -  500 /insatsen
            return false;
        }
        money = money - bet;    // retunerar hur mycket pengar som finns kvar efter bet
        return true;
    }

    public  void win (int bet){    // när spelaren vinner  tar han dubbelt så mycket av satsningen
        bet = bet * 2;             // vinner dealerns pengar
        money = money + bet;


    }


    public void equls(int bet){ // Om det blir oavgjort så får
                                  // man satsningen  tillbaka
        money = money + bet;

    }

    @Override                               // skriver ut namn och pengar
    public String toString() {
        return "name= '" + name + '\'' + ", money= " + money;
    }
}
