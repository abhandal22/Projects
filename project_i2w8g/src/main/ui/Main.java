package ui;

import java.io.FileNotFoundException;

public class Main {
    //EFFECTS: Creates a new MovieApp. (Runs the application)
    public static void main(String[] args) {
        try {
            new MovieApp();
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
        }
    }
}
