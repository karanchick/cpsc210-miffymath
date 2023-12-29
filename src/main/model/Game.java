package model;

// represents the state of the game and tracks when game ends (when a q ends)
// through states 1(active) and 0(game over)
public class Game {
    private static int ACTIVE = 1;
    private static int OVER = 0;

    private int state;

    // EFFECTS: makes a game with a base state of 1 (active)
    public Game() {
        state = 0;
    }

    // EFFECTS: produces true if state = 0, meaning the game is over
    public Boolean isGameOver() {
        return state == 0;

    }

    // getters + setters
    public int getState() {
        return state;
    }

    // MODIFIES: this
    // EFFECTS: restarts game state to active (1)
    public void restartGame() {
        state = ACTIVE;
    }

    // MODIFIES: this
    // EFFECTS: ends game by putting state to over(0)
    public void endGame() {
        state = OVER;
    }
}
