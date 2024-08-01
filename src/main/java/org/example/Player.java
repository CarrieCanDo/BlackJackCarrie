package org.example;

import java.util.ArrayList;
import java.util.List;

// Player class
public class Player {
    private List<Card> hand;
    private int balance;

    public Player(int balance) {
        this.hand = new ArrayList<>();
        this.balance = balance;
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public int calculateHandValue() {
        int value = 0;
        int numAces = 0;

        for (Card card : this.hand) {
            value += card.getValue();
            if (card.getRank().equals("Ace")) {
                numAces++;
            }
        }

        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }

        return value;
    }

    public void printHand() {
        for (Card card : this.hand) {
            System.out.println(card);
        }
    }

    public int getBalance() {
        return balance;
    }

    public void updateBalance(int amount) {
        this.balance += amount;
    }

    public void resetHand() {
        this.hand.clear();
    }
}
