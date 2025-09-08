/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.hangmanwithmaven3;

import java.util.Scanner;

public class Hangmanwithmaven3 {

    public static void main(String[] args) {
        // Interactive mode
        GameManager manager = new GameManager();
        manager.startGame();
    }
}

// -------------------------
// Parent class: Player
// -------------------------
class Player {

    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// -------------------------
// Challenger class
// -------------------------
class Challenger extends Player {

    private String secretWord;
    private String[] hints = new String[2];

    public Challenger(String name, String secretWord, String hint1, String hint2) {
        super(name);
        this.secretWord = secretWord.toUpperCase();
        this.hints[0] = hint1;
        this.hints[1] = hint2;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public String[] getHints() {
        return hints;
    }
}

// -------------------------
// Guesser class
// -------------------------
class Guesser extends Player {

    public Guesser(String name) {
        super(name);
    }
}

// -------------------------
// GameManager class
// -------------------------
class GameManager {

    private Challenger challenger;
    private Guesser guesser;
    private Scanner scanner = new Scanner(System.in);
    private int maxChances = 5;

    // Default constructor for interactive mode
    public GameManager() {}

    // Constructor for unit testing
    public GameManager(Challenger challenger, Guesser guesser) {
        this.challenger = challenger;
        this.guesser = guesser;
    }

    // -------------------------
    // Interactive game
    // -------------------------
    public void startGame() {
        System.out.println("==============================================");
        System.out.println("           Welcome to Hangman Game!           ");
        System.out.println("==============================================");

        System.out.print("Enter Challenger's name: ");
        String challengerName = scanner.nextLine();

        System.out.print(challengerName + ", enter a secret word: ");
        String secretWord = scanner.nextLine();
        while (secretWord.trim().isEmpty() || secretWord.contains(" ")) {
            System.out.println("Invalid word. Enter a word with no spaces.");
            secretWord = scanner.nextLine();
        }

        System.out.print("Hint 1 (max 100 chars): ");
        String hint1 = scanner.nextLine();
        while (hint1.length() > 100) {
            System.out.println("Hint too long. Try again:");
            hint1 = scanner.nextLine();
        }

        System.out.print("Hint 2 (max 100 chars): ");
        String hint2 = scanner.nextLine();
        while (hint2.length() > 100) {
            System.out.println("Hint too long. Try again:");
            hint2 = scanner.nextLine();
        }

        challenger = new Challenger(challengerName, secretWord, hint1, hint2);

        System.out.print("Enter Guesser's name: ");
        String guesserName = scanner.nextLine();
        guesser = new Guesser(guesserName);

        playGame();
    }

    // -------------------------
    // Game logic
    // -------------------------
    public boolean checkLetter(char letter, char[] guessedWord) {
        boolean found = false;
        String secretWord = challenger.getSecretWord();
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                guessedWord[i] = letter;
                found = true;
            }
        }
        return found;
    }

    public boolean isWordComplete(char[] guessedWord) {
        return String.valueOf(guessedWord).equals(challenger.getSecretWord());
    }

    public void playGame() {
        String secretWord = challenger.getSecretWord();
        char[] guessedWord = new char[secretWord.length()];
        for (int i = 0; i < secretWord.length(); i++) guessedWord[i] = '_';

        int wrongAttempts = 0;
        boolean wordGuessed = false;

        while (wrongAttempts < maxChances && !wordGuessed) {
            System.out.println("\n==============================================");
            System.out.println("Hints: 1) " + challenger.getHints()[0]
                    + " | 2) " + challenger.getHints()[1]);
            System.out.println("Word: " + String.valueOf(guessedWord));
            drawStickman(wrongAttempts);
            System.out.println("Chances left: " + (maxChances - wrongAttempts));

            System.out.print("Enter a letter: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Invalid input. Enter a single letter.");
                continue;
            }

            char letter = input.charAt(0);
            if (!checkLetter(letter, guessedWord)) {
                wrongAttempts++;
                System.out.println("Wrong guess! The stickman is closer to being hanged...");
            } else {
                System.out.println("Good guess!");
            }

            wordGuessed = isWordComplete(guessedWord);
        }

        if (wordGuessed) {
            System.out.println("\nCongratulations " + guesser.getName()
                    + "! You guessed the word: " + secretWord);
            drawStickmanFree();
        } else {
            System.out.println("\nSorry " + guesser.getName() + ", you lost. The word was: " + secretWord);
            drawStickman(maxChances);
        }
    }

    private void drawStickman(int stage) {
        String[] stages = {
            "\n   \n   \n   \n   \n=====",
            "\n   O\n   \n   \n   \n=====",
            "\n   O\n   |\n   \n   \n=====",
            "\n   O\n  /|\n   \n   \n=====",
            "\n   O\n  /|\\\n   \n   \n=====",
            "\n   O\n  /|\\\n  / \n   \n=====",
            "\n   O\n  /|\\\n  / \\\n   \n====="
        };
        System.out.println(stages[stage]);
    }

    private void drawStickmanFree() {
        System.out.println("\n   O  <- He is free!");
        System.out.println("  /|\\");
        System.out.println("  / \\");
        System.out.println("=====");
    }

    // -------------------------
    // Methods for unit testing
    // -------------------------
    public String getWordProgress(char[] guessedWord) {
        return String.valueOf(guessedWord);
    }

    public int getMaxChances() {
        return maxChances;
    }
}
