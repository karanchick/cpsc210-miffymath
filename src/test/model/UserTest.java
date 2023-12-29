package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User u1;
    private MiffyCard m1;
    private MiffyCard m2;
    private MiffyCard m3;

    @BeforeEach
    public void runBefore() {
        u1 = new User("u1");

        m1 = new MiffyCard("m1", 3);
        m2 = new MiffyCard("m2", 3);
        m3 = new MiffyCard("m3", 5);

    }

    @Test
    public void testConstructor() {
        assertEquals("u1", u1.getName());
        assertEquals(0, u1.getCurrentScore());
        assertTrue(u1.getAllTimeScores().isEmpty());
        assertTrue(u1.getCollection().isEmpty());
    }

    @Test
    public void testAddPointOnce() {
        u1.addPoint();
        assertEquals(1, u1.getCurrentScore());
    }

    @Test
    public void testAddPointMultiple() {
        u1.addPoint();
        u1.addPoint();
        assertEquals(2, u1.getCurrentScore());
    }

    @Test
    public void testAddMiffyCardOnce() {
        u1.addMiffyCard(m1);
        assertEquals(1, u1.getCollection().size());
        //assertEquals(m1, u1.getCollection().get(0));
    }

    @Test
    public void testAddMiffyCardMultiple() {
        u1.addMiffyCard(m1);
        u1.addMiffyCard(m3);
        assertEquals(2, u1.getCollection().size());
        assertEquals(m1, u1.getCollection().get(0));
        assertEquals(m3, u1.getCollection().get(1));
    }

    @Test
    public void testFilterCollectionRarity() {
        u1.addMiffyCard(m1);
        u1.addMiffyCard(m2);
        u1.addMiffyCard(m3);
        u1.filterCollectionRarity(3);
        assertEquals(2, u1.filterCollectionRarity(3).size());
        assertEquals(m1, u1.getCollection().get(0));
        assertEquals(m2, u1.getCollection().get(1));
    }

    @Test
    public void testAddScoreToAllTimeOnce() {
        u1.addPoint();
        u1.addPoint();
        u1.addScoreToAllTime(u1.getCurrentScore());
        assertEquals(1, u1.getAllTimeScores().size());
        assertEquals(2, u1.getAllTimeScores().get(0));
        assertEquals(0, u1.getCurrentScore());
    }

    @Test
    public void testAddScoreToAllTimeMultiple() {
        u1.addPoint();
        u1.addPoint();
        u1.addScoreToAllTime(u1.getCurrentScore());
        u1.addPoint();
        u1.addScoreToAllTime(u1.getCurrentScore());
        assertEquals(2, u1.getAllTimeScores().size());
        assertEquals(2, u1.getAllTimeScores().get(0));
        assertEquals(1, u1.getAllTimeScores().get(1));
        assertEquals(0, u1.getCurrentScore());
    }

    @Test
    public void testHighestScore() {
        u1.addPoint();
        u1.addPoint();
        u1.addScoreToAllTime(u1.getCurrentScore());
        u1.addPoint();
        u1.addScoreToAllTime(u1.getCurrentScore());
        assertEquals(2, u1.highestScore());

    }

    @Test
    public void testCollectionToString() {
        u1.addMiffyCard(m1);
        u1.addMiffyCard(m2);

        assertEquals(u1.collectionToString(u1.getCollection()),u1.collectionToString(u1.getCollection()));
    }
}