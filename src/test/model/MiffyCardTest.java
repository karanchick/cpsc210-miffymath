package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MiffyCardTest {
    private MiffyCard m5;
    private MiffyCard m0;
    private MiffyCard m1;
    private MiffyCard m2;
    private MiffyCard m3;
    private MiffyCard m4;

    @BeforeEach
    public void runBefore() {
        m5 = new MiffyCard("m5", 5);
        m0 = new MiffyCard("m0", 0);
        m1 = new MiffyCard("m1", 1);
        m2 = new MiffyCard("m2", 2);
        m3 = new MiffyCard("m3", 3);
        m4 = new MiffyCard("m4", 4);
    }

    @Test
    public void testConstructor() {
        assertEquals("m5", m5.getName());
        assertEquals(5, m5.getRarity());

    }

    @Test
    public void testConvertRarityOne() {
        assertTrue(m0.convertRarity().equals("basic!"));
        assertTrue(m1.convertRarity().equals("not bad!"));
        assertTrue(m2.convertRarity().equals("rare!"));
        assertTrue(m3.convertRarity().equals("super rare!"));
        assertTrue(m4.convertRarity().equals("mythical!"));
        assertTrue(m5.convertRarity().equals("legendary!!"));

    }

}
