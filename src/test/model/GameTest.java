package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game g1;

    @BeforeEach
    public void runBefore() {
        g1 = new Game();

    }

    @Test
    public void testConstructor() {
        assertEquals(0, g1.getState());

    }

    @Test
    public void testGameOver() {
        g1.endGame();
        assertTrue(g1.isGameOver());

    }

    @Test
    public void testNotGameOver() {
        g1.endGame();
        g1.restartGame();
        assertFalse(g1.isGameOver());

    }

}
