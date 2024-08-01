package org.example;

import java.util.Scanner;

// GameRunner class
public class GameRunner {

    public static void main(String[] args) {
        // play some music
        String filepath = "CasinoJazz.wav";
        PlayMusic music = new PlayMusic();
        music.playMusic(filepath);

        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();

        Player player = new Player(100); // Starting balance
        Player dealer = new Player(0); // Dealer doesn't have a balance

        boolean playing = true;
        while (playing) {
            player.resetHand();
            dealer.resetHand();
            System.out.println("Starting a new round...");

            // Deal initial cards
            player.addCard(deck.dealNextCard());
            dealer.addCard(deck.dealNextCard());
            player.addCard(deck.dealNextCard());
            dealer.addCard(deck.dealNextCard());

            // Player's turn
            boolean playerTurn = true;
            while (playerTurn) {
                System.out.println("Your hand:");
                player.printHand();
                System.out.println("Your hand value: " + player.calculateHandValue());
                System.out.println("Do you want to (1) Hit or (2) Stand?");
                int choice = sc.nextInt();

                if (choice == 1) {
                    player.addCard(deck.dealNextCard());
                    if (player.calculateHandValue() > 21) {
                        System.out.println("You busted!");
                        playerTurn = false;
                    }
                } else {
                    playerTurn = false;
                }
            }

            // Dealer's turn
            while (dealer.calculateHandValue() < 17) {
                dealer.addCard(deck.dealNextCard());
            }

            System.out.println("Dealer's hand:");
            dealer.printHand();
            System.out.println("Dealer's hand value: " + dealer.calculateHandValue());

            // Determine winner
            int playerValue = player.calculateHandValue();
            int dealerValue = dealer.calculateHandValue();
            if (playerValue > 21) {
                System.out.println("You lose!");
                player.updateBalance(-10);
            } else if (dealerValue > 21 || playerValue > dealerValue) {
                System.out.println("You win!");
                player.updateBalance(10);
            } else if (playerValue < dealerValue) {
                System.out.println("You lose!");
                player.updateBalance(-10);
            } else {
                System.out.println("It's a tie!");
            }

            System.out.println("Your new balance: " + player.getBalance());
            System.out.println("Do you want to play again? (1) Yes (2) No");
            int playAgain = sc.nextInt();
            if (playAgain != 1) {
                playing = false;
            }
        }

        System.out.println("Thanks for playing!");
    }
}
