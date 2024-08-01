package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Deck class
public class Deck implements DeckActions {
    private List<Card> myCards;
    private int numCards;

    public Deck() {
        this.myCards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                this.myCards.add(new Card(suit, ranks[i], values[i]));
            }
        }

        this.numCards = this.myCards.size();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.myCards);
    }

    @Override
    public Card dealNextCard() {
        return this.myCards.remove(this.myCards.size() - 1);
    }

    @Override
    public void printDeck(int numToPrint) {
        for (int i = 0; i < numToPrint; i++) {
            System.out.println(this.myCards.get(i));
        }
    }
}
