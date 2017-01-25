package example.codeclan.com.cardgames;

/**
 * Created by user on 20/01/2017.
 */

import java.util.*;

public class Player{

    private String name;
    private ArrayList<Card> hand;
    private int total;
    boolean stop = false;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

    public String getName(){
        return this.name;
    }

    public int handSize(){
        return hand.size();
    }

    public void intoHand(Card card_input){
        hand.add(card_input);
    }

    public void outOfHand(int input){
        hand.remove(input);
    }

    public void clear(){
        hand.clear();
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    public void setTotal(int num){
        total = num;
    }

    public int getTotal(){
        return this.total;
    }

    public void setStop(boolean input){
        stop = input;
    }

    public boolean getStop(){
        return this.stop;
    }

}
