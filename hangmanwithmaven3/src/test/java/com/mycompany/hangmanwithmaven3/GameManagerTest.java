/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.hangmanwithmaven3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameManagerTest {

    private Challenger challenger;
    private Guesser guesser;
    private GameManager gameManager;

    @BeforeEach
    void setUp() {
        // Create a Challenger with word "JAVA" and hints
        challenger = new Challenger("Alice", "JAVA", "Programming language", "Starts with J");
        guesser = new Guesser("Bob");
        // Create GameManager with these players
        gameManager = new GameManager(challenger, guesser);
    }

    @Test
    void testCheckLetterCorrect() {
        char[] guessedWord = {'_', '_', '_', '_'};
        boolean result = gameManager.checkLetter('J', guessedWord);
        assertTrue(result); // Letter exists
        assertEquals('J', guessedWord[0]); // Letter placed in correct position
    }

    @Test
    void testCheckLetterIncorrect() {
        char[] guessedWord = {'_', '_', '_', '_'};
        boolean result = gameManager.checkLetter('X', guessedWord);
        assertFalse(result); // Letter doesn't exist
        assertEquals("____", gameManager.getWordProgress(guessedWord));
    }

    @Test
    void testIsWordCompleteFalse() {
        char[] guessedWord = {'J', 'A', '_', 'A'};
        assertFalse(gameManager.isWordComplete(guessedWord));
    }

    @Test
    void testIsWordCompleteTrue() {
        char[] guessedWord = {'J', 'A', 'V', 'A'};
        assertTrue(gameManager.isWordComplete(guessedWord));
    }

    @Test
    void testMaxChances() {
        assertEquals(5, gameManager.getMaxChances());
    }
}
