package persistence;

import model.MiffyCard;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    // CITATION: this was class was modelled off of WorkRoom

    @Test
    void testWriterInvalidFile() {
        try {
            User u = new User("player");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterNoScoreEmptyCollection() {
        try {
            User u = new User("player");
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(u);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            u = reader.read();
            assertEquals("player", u.getName());
            assertEquals(0, u.getAllTimeScores().size());
            assertEquals(0, u.getCollection().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

    @Test
    void testWriterWithData() {
        try {
            User u = new User("player");
            u.addMiffyCard(new MiffyCard("1", 5));
            u.addMiffyCard(new MiffyCard("2", 2));
            u.addScoreToAllTime(5);
            u.addScoreToAllTime(1);
            JsonWriter writer = new JsonWriter("./data/testWriterWithData.json");
            writer.open();
            writer.write(u);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterWithData.json");
            u = reader.read();
            assertEquals("player", u.getName());
            assertEquals(2, u.getAllTimeScores().size());
            assertEquals(5, u.getAllTimeScores().get(0));
            assertEquals(1, u.getAllTimeScores().get(1));

            List<MiffyCard> miffys = u.getCollection();
            assertEquals(2, miffys.size());
            checkCard("1", 5, miffys.get(0));
            checkCard("2", 2, miffys.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }



}
