package model;

// TO DO: ADD CONSTANTS, point value, number of qs to get miffy

// represents a user, with a name, a score of their game, and a list of
// scores from all games ever played

import org.json.JSONObject;
import persistence.CanWrite;

import java.util.ArrayList;
import java.util.List;

public class User implements CanWrite {
    private static int POINT_VAL = 1;

    private String name;
    private int currentScore;
    private List<Integer> allTimeScores;
    private List<MiffyCard> collection;

    // REQUIRES: name to be a String of non-zero length
    // EFFECTS: creates a new user, with a name, current score of 0,
    // an empty list of all time scores, and an empty list of MiffyCards
    public User(String name) {
        this.name = name;
        currentScore = 0;
        collection = new ArrayList<>();
        allTimeScores = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds POINT_VALUE to user's current score
    public void addPoint() {
        this.currentScore = this.currentScore + POINT_VAL;
        EventLog.getInstance().logEvent(new Event("Point added!"));
    }

    // MODIFIES: collection
    // EFFECTS: adds a Miffy Card to user's collection
    public void addMiffyCard(MiffyCard m) {
        collection.add(m);
        EventLog.getInstance().logEvent(new Event("Miffy Card added!"));
    }

    // REQUIRES: collection must be non-empty
    // EFFECTS: returns all Miffy Cards in collection of a certain rarity, in order they were gotten
    public List<MiffyCard> filterCollectionRarity(int rarity) {
        ArrayList<MiffyCard> result = new ArrayList<>();
        for (MiffyCard m : collection) {
            if (m.getRarity() == rarity) {
                result.add(m);
            }
        }
        EventLog.getInstance().logEvent(new Event("Sorted collection by rarity!"));
        return result;
    }

    // EFFECTS: creates a single string that holds the name of every card in user collection
    public String collectionToString(List<MiffyCard> collection) {
        String result = "";
        for (MiffyCard m : collection) {
            result = result.concat("  " + m.getName());
        }

        return result;
    }


    // REQUIRES: current score > 0
    // MODIFIES: allTimeScores
    // EFFECTS: adds user's current score to the list of all time scores
    // and resets user's current score to 0
    public void addScoreToAllTime(int score) {
        allTimeScores.add(score);
        resetCurrentScore();
    }

    // REQUIRES: list of allTimeScores is non-empty
    // EFFECTS: returns the highest score user has earned so far
    public int highestScore() {
        int best = 0;
        for (int score : allTimeScores) {
            if (score > best) {
                best = score;
            }
        }
        return best;
    }

    //getters + setters

    // EFFECTS: returns user's name
    public String getName() {
        return name;
    }

    // EFFECTS: returns list of users scores of all time
    public List<Integer> getAllTimeScores() {
        return allTimeScores;
    }

    // EFFECTS: returns user's current score
    public int getCurrentScore() {
        return currentScore;
    }

    // EFFECTS: returns user's miffycard collection
    public List<MiffyCard> getCollection() {
        return collection;
    }

    // EFFECTS: resets current scores to 0
    public void resetCurrentScore() {
        this.currentScore = 0;
        EventLog.getInstance().logEvent(new Event("Incorrect answer :( Game ended!"));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("scores", allTimeScores);
        json.put("collection", collection);
        return json;
    }
}



