package persistence;

import model.MiffyCard;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    // CITATION: this was class was modelled off of WorkRoom
    protected void checkCard(String name, int rarity, MiffyCard card) {
        assertEquals(name, card.getName());
        assertEquals(rarity, card.getRarity());
    }
}
