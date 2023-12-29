package model;

import org.json.JSONObject;
import persistence.CanWrite;

import java.util.List;


// represents a MiffyCard, with name and rarity(out of 5), that is given as a reward for answering questions.
public class MiffyCard {
    private final String name;
    private final int rarity;

    // REQUIRES: name of card to have non-zero length
    // EFFECTS: creates a MiffyCard with a given name and rarity
    public MiffyCard(String name, int rarity) {
        this.name = name;
        this.rarity = rarity;
    }

    // EFFECTS: returns the corresponding String rarity, depending on Miffy Card's rarity
    public String convertRarity() {
        if (this.rarity == 0) {
            return "basic!";
        } else if (this.rarity == 1) {
            return "not bad!";
        } else if (this.rarity == 2) {
            return "rare!";
        } else if (this.rarity == 3) {
            return "super rare!";
        } else if (this.rarity == 4) {
            return "mythical!";
        } else {
            return "legendary!!";
        }
    }


    //getters

    // EFFECTS: returns name of card
    public String getName() {
        return name;
    }

    // EFFECTS: returns rarity of card in number
    public int getRarity() {
        return rarity;
    }


}
