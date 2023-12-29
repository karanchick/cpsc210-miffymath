package persistence;

import model.MiffyCard;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    // CITATION: this was class was modelled off of WorkRoom

    @Test
    void testReaderFakeFile() {
        JsonReader reader = new JsonReader("./data/doesntExist.json");
        try {
            User u = reader.read();
            fail("IOException expected here");
        } catch (IOException e) {
            //passes
        }
    }

    @Test
    void testReaderEmpty() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            User u = reader.read();
            assertEquals("player", u.getName());
            assertEquals(0, u.getAllTimeScores().size());
            assertEquals(0, u.getCollection().size());
        } catch (IOException e) {
            fail("Couldn't read the file");
        }
    }

    @Test
    void testReaderWithData() {
        JsonReader reader = new JsonReader("./data/testReaderWithData.json");
        try {
            User u = reader.read();

            assertEquals("player", u.getName());
            assertEquals(2, u.getAllTimeScores().size());
            assertEquals(5, u.getAllTimeScores().get(0));
            assertEquals(1, u.getAllTimeScores().get(1));

            List<MiffyCard> miffys = u.getCollection();
            assertEquals(2, miffys.size());
            checkCard("1", 5, miffys.get(0));
            checkCard("2", 2, miffys.get(1));
        } catch (IOException e) {
            fail("Couldn't read file");
        }
    }
}
