package example.codeclan.com.cardgames;

/**
 * Created by user on 20/01/2017.
 */

import java.util.*;

public class Game{

    String name;
    Card card;
    ArrayList<Card> deck = new ArrayList<>();

    String Array[];

    // ask for the game the player wants to play, set it up from here VVVV

    public Game(String i_name){
        name = i_name;

        //this is being skipped -- FIX IT
        if(name.equals("HighCard")){

            int i = 0;
            int div = 0;

            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    i += 1;
                    card = new Card(rank);
                    card.setType(suit);
                    card.setNum(i);

                    deck.add(card);

                }
                i = 0;
            }

        }

        if(name.equals("Simple BlackJack")){

            int i = 0;
            int div = 0;
            int card_num = 0;
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    i += 1;
                    card_num = i;
                    card = new Card(rank);
                    card.setType(suit);
                    card.setNum(i);

                    if(card_num > 10){
                        card_num = 10;
                    }

                    card.setNum(card_num);

                    deck.add(card);

                }
                i = 0;
            }
        }

        if(name.equals("BlackJack")){

            int i = 0;
            int div = 0;
            int card_num = 0;
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    i += 1;
                    card_num = i;
                    card = new Card(rank);
                    card.setType(suit);
                    card.setNum(i);

                    if(card_num > 10){
                        card_num = 10;
                    }

                    card.setNum(card_num);

                    deck.add(card);

                }
                i = 0;
            }



        }

    }

    public String getName(){
        return this.name;
    }

    public int deckSize(){
        return deck.size();
    }

    public void intoDeck(Card card_input){
        deck.add(card_input);
    }

    public void outOfDeck(int input){
        deck.remove(input);
    }

    public void clear(){
        deck.clear();
    }

    public ArrayList<Card> getDeck(){
        return deck;
    }

}