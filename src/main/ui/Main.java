package ui;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends JFrame {
    public static void main(String[] args) {
        try {
            new MiffyMath();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found :(");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
