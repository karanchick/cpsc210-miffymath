package persistence;

import model.MiffyCard;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;


// Represents a reader that reads the users scores and collection from JSON data on file
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads user from file and returns it
    // throws IOException if data can't be read from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as a string and returns it
    // throws IOException if an error occurs reading or returning string
    private String readFile(String source) throws IOException {
        StringBuilder contentCreator = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentCreator.append(s));
        }

        return contentCreator.toString();
    }

    // EFFECTS: parses user from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        User u = new User(name);
        addScores(u, jsonObject);
        addCollection(u, jsonObject);
        return u;
    }

    // MODIFIES: u
    // EFFECTS: parses scores from JSON object and adds them to workroom
    private void addScores(User u, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("scores");
        for (Object json : jsonArray) {
            int nextScore = (int) json;
            u.addScoreToAllTime(nextScore);
        }
    }

    // MODIFIES: u
    // EFFECTS: parses collection (of cards) from JSON object and adds it to user
    private void addCollection(User u, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("collection");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCard(u, nextCard);
        }
    }

    // MODIFIES: u
    // EFFECTS: parses card from JSON object and adds to user
    private void addCard(User u, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int rarity = jsonObject.getInt("rarity");
        MiffyCard card = new MiffyCard(name, rarity);
        u.addMiffyCard(card);
    }
}
