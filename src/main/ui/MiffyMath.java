package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MiffyMath extends JFrame implements ActionListener, WindowListener {

    private static final String JSON_STORE = "./data/user.json";
    private User player;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Problem problem;
    private int miffyCardCounter;


    // FONTS and COLORS:
    Font titleFont = new Font("Courier", Font.BOLD, 45);
    Font headingFont = new Font("Courier", Font.BOLD, 75);
    Font scoreFont = new Font("Courier", Font.BOLD, 30);
    Font subFont = new Font("Courier", Font.ITALIC, 25);
    Color mainPink = new Color(241, 181, 203);
    Color red = new Color(214, 64, 69);
    Color green = new Color(202, 255, 138);
    Color yellow = new Color(255, 214, 112);
    Color blue = new Color(132, 175, 230);
    Color black = new Color(72, 74, 71);

    // FOR START MENU:
    JLabel titleLabel = new JLabel("Miffy Math <3");
    JLabel nameLabel = new JLabel("Enter name: ");
    JTextField nameField = new JTextField(15);
    JButton playButton = new JButton("Play!");
    JButton loadButton = new JButton("Load Game");
    JButton saveButton = new JButton("Save Game");
    JButton scoresButton = new JButton("My Scores");
    JButton collectionButton = new JButton("My Collection");

    // FOR GAME MENU:
    JLabel questionLabel = new JLabel("Here's your question");
    JTextField questionField = new JTextField(14);
    JLabel answerLabel = new JLabel("Your answer: ");
    JTextField answerField = new JTextField(25);
    JTextField scoreCounterField = new JTextField(5);
    JTextField resultField = new JTextField(12);
    JTextField miffyCounterField = new JTextField(5);
    JLabel miffyAlert = new JLabel("woohoo! new miffy card!!");
    BufferedImage miffyPop1 = ImageIO.read(new File("src/main/ui/miffypop.png"));
    Image miffyPop2 = miffyPop1.getScaledInstance(300, 220, Image.SCALE_DEFAULT);
    JLabel miffyPopLabel = new JLabel(new ImageIcon(miffyPop2));




    // FOR POST GAME:
    JLabel gameOverLabel = new JLabel("Game over!");
    JLabel endScoreLabel = new JLabel("Your score was: ");
    JTextField endScoreField = new JTextField(5);
    JButton postToMenuButton = new JButton("Back to menu");

    // FOR SCORES SCREEN:
    JLabel highScoreLabel = new JLabel("High Score: ");
    JTextField highScoreField = new JTextField(5);
    JLabel allScoresLabel = new JLabel("All Scores: ");
    JTextField allScoresField = new JTextField(25);
    JButton scoreToMenuButton = new JButton("Back to menu");

    // FOR COLLECTION SCREEN:
    JLabel allMiffyCardsLabel = new JLabel("All Miffy Cards: ");
    JTextField allMiffyCardsField = new JTextField(25);
    JLabel sortRarityLabel = new JLabel("Sort by rarity: ");
    JTextField sortRarityField = new JTextField(5);
    JButton collectionToMenuButton = new JButton("Back to menu");
    private Event[] eventLog;

    // EFFECTS: Initializes a new MiffyMath game
    // throws FileNotFoundException if error finding JSON_STORE
    public MiffyMath() throws IOException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        miffyCardCounter = 0;

        loadStartMenu();
        makeCurrProb();


        runMiffyMath();
    }

    private void makeCurrProb() {
        problem = new Problem();
    }

    // EFFECTS: Loads the Start Menu for the game UI
    @SuppressWarnings("methodlength")
    private void loadStartMenu() {

        setLayout(new FlowLayout());
        add(titleLabel);
        add(nameLabel);
        add(nameField);
        add(playButton);
        add(loadButton);
        add(scoresButton);
        add(collectionButton);
        add(saveButton);

        //event listeners:
        playButton.addActionListener(this);
        scoresButton.addActionListener(this);
        collectionButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        addWindowListener(this);


        titleLabel.setFont(titleFont);
        nameLabel.setFont(subFont);
        nameField.setFont(subFont);
        playButton.setFont(headingFont);
        loadButton.setFont(subFont);
        scoresButton.setFont(subFont);
        collectionButton.setFont(subFont);
        saveButton.setFont(subFont);


        getContentPane().setBackground(blue);
        titleLabel.setForeground(yellow);
        playButton.setForeground(mainPink);
        nameLabel.setForeground(Color.white);
        nameField.setForeground(blue);
        resultField.setForeground(Color.white);
        loadButton.setForeground(yellow);
        scoresButton.setForeground(yellow);
        collectionButton.setForeground(yellow);
        saveButton.setForeground(yellow);


        setTitle("Miffy Math Game");
        setSize(400, 700);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    // EFFECTS: loads the game screen elements in the UI
    @SuppressWarnings("methodlength")
    private void loadGameScreen() {
        add(questionLabel);
        add(questionField);
        add(answerLabel);
        add(answerField);
        add(scoreCounterField);
        add(resultField);
        add(miffyCounterField);
        add(miffyAlert);
        add(miffyPopLabel);


        getContentPane().setBackground(blue);
        questionLabel.setForeground(Color.white);
        questionField.setForeground(mainPink);
        answerLabel.setForeground(Color.white);
        answerField.setForeground(mainPink);
        resultField.setForeground(black);
        scoreCounterField.setBackground(yellow);
        miffyCounterField.setBackground(mainPink);
        miffyCounterField.setForeground(black);
        scoreCounterField.setForeground(black);


        questionLabel.setFont(subFont);
        questionField.setFont(titleFont);
        questionField.setEditable(false);
        answerLabel.setFont(subFont);
        answerField.setFont(subFont);
        scoreCounterField.setFont(subFont);
        scoreCounterField.setEditable(false);
        resultField.setFont(subFont);
        resultField.setEditable(false);
        miffyCounterField.setFont(subFont);
        miffyCounterField.setEditable(false);

        miffyAlert.setVisible(false);
        miffyPopLabel.setVisible(false);
        answerField.addActionListener(this);

    }

    // EFFECTS: loads the scores screen elements in the UI
    private void loadScoresMenu() {
        add(highScoreLabel);
        add(highScoreField);
        add(allScoresLabel);
        add(allScoresField);
        add(scoreToMenuButton);

        scoreToMenuButton.addActionListener(this);

        highScoreLabel.setFont(scoreFont);
        highScoreField.setFont(subFont);
        allScoresLabel.setFont(scoreFont);
        allScoresField.setFont(subFont);
        scoreToMenuButton.setFont(subFont);

        highScoreField.setEditable(false);
        allScoresField.setEditable(false);

        highScoreLabel.setForeground(Color.WHITE);
        highScoreField.setForeground(mainPink);
        allScoresLabel.setForeground(Color.WHITE);
        allScoresField.setBackground(yellow);
        allScoresField.setForeground(Color.white);
        scoreToMenuButton.setForeground(mainPink);


    }

    // EFFECTS: loads the collection screen elements in the UI
    private void loadCollectionMenu() {
        add(allMiffyCardsLabel);
        add(allMiffyCardsField);
        add(sortRarityLabel);
        add(sortRarityField);
        add(collectionToMenuButton);

        collectionToMenuButton.addActionListener(this);
        sortRarityField.addActionListener(this);

        allMiffyCardsLabel.setFont(scoreFont);
        allMiffyCardsField.setFont(subFont);
        sortRarityLabel.setFont(subFont);
        sortRarityField.setFont(subFont);
        collectionToMenuButton.setFont(subFont);

        allMiffyCardsField.setEditable(false);

        allMiffyCardsLabel.setForeground(Color.WHITE);
        sortRarityLabel.setForeground(Color.WHITE);
        collectionToMenuButton.setForeground(mainPink);
        allMiffyCardsField.setBackground(mainPink);
        allMiffyCardsField.setForeground(Color.white);

    }

    // EFFECTS: loads the post-game screen elements in the UI
    private void loadPostMenu() {

        add(gameOverLabel);
        add(endScoreLabel);
        add(endScoreField);
        add(postToMenuButton);

        gameOverLabel.setFont(titleFont);
        endScoreLabel.setFont(subFont);
        endScoreField.setFont(subFont);
        postToMenuButton.setFont(subFont);
        endScoreField.setEditable(false);

        gameOverLabel.setForeground(red);


        postToMenuButton.addActionListener(this);

    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMiffyMath() {
        boolean running = true;
        String command;

        initUser();

        while (running) {
            showMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\ntill next time!");
    }

    // MODIFIES: this
    // EFFECTS: processes users home screen command
    private void processCommand(String command) {
        if (command.equals("p")) {
            startGame();
        } else if (command.equals("l")) {
            loadAll();
        } else if (command.equals("h")) {
            seeHighscores();
        } else if (command.equals("c")) {
            seeCollection();
        } else if (command.equals("s")) {
            saveAll();
        } else {
            System.out.println("Oops! That's not an option...");
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes a user, lets person assign name
    private void initUser() {
        player = new User("");

        System.out.println("\nWelcome to MiffyMath!");
        System.out.println("\nEnter your name");
        input = new Scanner(System.in);
        String n = input.next();
        player = new User(n);
        System.out.println("Nice to meet you " + player.getName());
    }

    // EFFECTS: shows menu options for user
    private void showMenu() {
        System.out.println("\nWelcome to MiffyMath!");
        System.out.println("\tp -> start a new game");
        System.out.println("\tl -> load previous game");
        System.out.println("\th -> see previous scores");
        System.out.println("\tc -> see MiffyCard collection");
        System.out.println("\ts -> save current game");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: begins a miffy math game
    @SuppressWarnings("methodlength")
    private void startGame() {
        int required = 2;
        System.out.println("\nhere's the rules, answer " + required
                + " questions correctly in a row, get a MiffyCard!");
        System.out.println("but answer wrong... and the game ends!");
        Game game = new Game();
        game.restartGame();

        while (game.getState() == 1) {
            Problem problem = new Problem();
            System.out.println("here's your problem: ");
            System.out.println(problem.getN1() + problem.getOperation() + problem.getN2());

            input = new Scanner(System.in);
            int a = Integer.parseInt(input.next());

            if (a == problem.getResult()) {
                player.addPoint();
                System.out.println("good work! here's the next question");
                if (player.getCurrentScore() % required == 0) { // gives miffy card, after required amt of right ans
                    String randName = Integer.toString(new Random().nextInt(100));
                    int randRarity = new Random().nextInt(6);

                    player.addMiffyCard(new MiffyCard(randName, randRarity)); // future to continue, make real names
                    System.out.println("yay! you just got a MiffyCard!");
                }
            } else {
                game.endGame();
            }
        }

        System.out.println("game over! your score was " + player.getCurrentScore());
        player.addScoreToAllTime(player.getCurrentScore());
       // player.resetCurrentScore();

    }

    // EFFECTS:: displays all time scores, and highest score of user
    private void seeHighscores() {
        System.out.println("\nhere are all your scores so far: ");
        player.getAllTimeScores().forEach(System.out::println);
        System.out.println("\nand your highest score so far is: " + player.highestScore());

    }

    // EFFECTS: displays user's miffy card collection
    private void seeCollection() {
        System.out.println("\nhere are all your MiffyCards so far: ");
        List<MiffyCard> completeCo = player.getCollection();
        for (MiffyCard m : completeCo) {
            System.out.println("name: " + m.getName());
            System.out.println("rarity: " + m.getRarity());
            System.out.println("-------------");
        }

        System.out.println("\nto sort by rarity, enter rarity here: ");
        input = new Scanner(System.in);
        int r = Integer.parseInt(input.next());
        List<MiffyCard> rareEnough = player.filterCollectionRarity(r);
        for (MiffyCard m : rareEnough) {
            System.out.println("name: " + m.getName());
            System.out.println("rarity: " + m.convertRarity());
            System.out.println("-------------");
        }
    }

    // EFFECTS: saves user to file
    private void saveAll() {
        try {
            jsonWriter.open();
            jsonWriter.write(player);
            jsonWriter.close();
            System.out.println("Saved " + player.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads user from file with scores and collection
    private void loadAll() {
        try {
            player = jsonReader.read();
            System.out.println("Loaded " + player.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: sets all start menu UI elements to not-visible
    private void clearStartMenu() {
        nameLabel.setVisible(false);
        nameField.setVisible(false);
        playButton.setVisible(false);
        loadButton.setVisible(false);
        scoresButton.setVisible(false);
        collectionButton.setVisible(false);
        saveButton.setVisible(false);

    }

    // EFFECTS: sets all start menu UI elements to visible
    private void returnStartMenu() {
        nameLabel.setVisible(true);
        nameField.setVisible(true);
        playButton.setVisible(true);
        loadButton.setVisible(true);
        scoresButton.setVisible(true);
        collectionButton.setVisible(true);
        saveButton.setVisible(true);
    }

    // EFFECTS: sets score menu elements to not-visible
    private void clearScoreMenu() {
        highScoreLabel.setVisible(false);
        highScoreField.setVisible(false);
        allScoresLabel.setVisible(false);
        allScoresField.setVisible(false);

        scoreToMenuButton.setVisible(false);
    }

    // EFFECTS: sets score menu elements to visible
    private void returnScoreMenu() {
        highScoreLabel.setVisible(true);
        highScoreField.setVisible(true);
        allScoresLabel.setVisible(true);
        allScoresField.setVisible(true);

        scoreToMenuButton.setVisible(true);
    }

    // EFFECTS: sets collection menu elements to not-visible
    private void clearCollectionMenu() {
        allMiffyCardsLabel.setVisible(false);
        allMiffyCardsField.setVisible(false);
        sortRarityField.setVisible(false);
        sortRarityLabel.setVisible(false);

        collectionToMenuButton.setVisible(false);
    }

    // EFFECTS: sets collection menu elements to not-visible
    private void returnCollectionMenu() {
        allMiffyCardsLabel.setVisible(true);
        allMiffyCardsField.setVisible(true);
        sortRarityField.setVisible(true);
        sortRarityLabel.setVisible(true);

        collectionToMenuButton.setVisible(true);
    }

    // EFFECTS: sets game menu elements to not-visible
    private void clearGameMenu() {
        questionLabel.setVisible(false);
        questionField.setVisible(false);
        answerLabel.setVisible(false);
        answerField.setVisible(false);
        scoreCounterField.setVisible(false);
        resultField.setVisible(false);
        miffyCounterField.setVisible(false);
    }

    // EFFECTS: sets game menu elements to visible and resets the elements to default state
    private void restartGameMenu() {
        questionLabel.setVisible(true);
        questionField.setVisible(true);
        answerLabel.setVisible(true);
        answerField.setVisible(true);
        scoreCounterField.setVisible(true);
        resultField.setVisible(true);
        miffyCounterField.setVisible(true);
        miffyAlert.setVisible(false);
        miffyPopLabel.setVisible(false);

        resultField.setBackground(Color.white);
        resultField.setText("");
        scoreCounterField.setText(Integer.toString(player.getCurrentScore()));
        miffyCounterField.setText(null);

        answerField.setEditable(true);
    }

    // EFFECTS: sets game menu elements to not-visible
    private void clearPostMenu() {
        gameOverLabel.setVisible(false);
        endScoreField.setVisible(false);
        endScoreLabel.setVisible(false);
        postToMenuButton.setVisible(false);
    }

    // EFFECTS: sets game menu elements to visible and reset states
    private void returnPostMenu() {
        gameOverLabel.setVisible(true);
        endScoreField.setVisible(true);
        endScoreLabel.setVisible(true);
        postToMenuButton.setVisible(true);
        endScoreField.setText("");
    }

    @SuppressWarnings("methodlength")
    @Override
    public void actionPerformed(ActionEvent e) {
        int correctAns = problem.getResult();
        String question = problem.getQuestion();
        int required = 2;


        if (e.getSource() == playButton) {
            clearStartMenu();
            loadGameScreen();
            restartGameMenu();

            questionField.setText(question);
        }

        if (e.getSource() == loadButton) {
            loadAll();
        }

        if (e.getSource() == saveButton) {
            saveAll();
        }

        if (e.getSource() == scoresButton) {
            clearStartMenu();
            loadScoresMenu();
            returnScoreMenu();

            highScoreField.setText(Integer.toString(player.highestScore()));
            allScoresField.setText(listsToString(player.getAllTimeScores()));
        }

        if (e.getSource() == collectionButton) {
            clearStartMenu();
            loadCollectionMenu();
            returnCollectionMenu();

            allMiffyCardsField.setText(player.collectionToString(player.getCollection()));
        }

        if (e.getSource() == sortRarityField) {
            int userAnswer = Integer.parseInt(sortRarityField.getText());
            allMiffyCardsField.setText(player.collectionToString(player.filterCollectionRarity(userAnswer)));
        }

        if (e.getSource() == scoreToMenuButton) {
            clearScoreMenu();
            returnStartMenu();
        }

        if (e.getSource() == collectionToMenuButton) {
            clearCollectionMenu();
            returnStartMenu();
        }

        if (e.getSource() == postToMenuButton) {
            restartGameMenu();
            clearGameMenu();
            clearPostMenu();
            returnStartMenu();
        }

        if (e.getSource() == answerField) {
            int userAnswer = Integer.parseInt(answerField.getText());

            miffyAlert.setVisible(false);
            miffyPopLabel.setVisible(false);

            if (userAnswer == correctAns) {
                player.addPoint();
                scoreCounterField.setText(Integer.toString(player.getCurrentScore()));
                resultField.setText("yay!");
                resultField.setBackground(green);
                resultField.setForeground(black);
                answerField.setText("");
                problem = new Problem();
                questionField.setText(problem.getQuestion());

                if (player.getCurrentScore() % required == 0) { // gives miffy card, after required amt of right ans
                    String randName = Integer.toString(new Random().nextInt(100));
                    int randRarity = new Random().nextInt(6);

                    player.addMiffyCard(new MiffyCard(randName, randRarity)); // future to continue, make real names
                    miffyCardCounter++;

                    miffyCounterField.setText(Integer.toString(miffyCardCounter));
                    miffyAlert.setVisible(true);
                    miffyPopLabel.setVisible(true);
                }
            } else {
                resultField.setText("wrong :(");
                resultField.setBackground(red);
                answerField.setText("");
                resultField.setForeground(Color.white);
                answerField.setEditable(false);

                returnPostMenu();
                loadPostMenu();

                endScoreField.setText(Integer.toString(player.getCurrentScore()));
                player.addScoreToAllTime(player.getCurrentScore());
                player.resetCurrentScore();
                miffyCardCounter = 0;
            }

        }
    }

    // EFFECTS: creates a single string that lists every element in convertee
    private String listsToString(List convertee) {
        String result = "";
        for (Object s : convertee) {
            result = result.concat("  " + s);
        }
        return result;

    }

    private void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}









