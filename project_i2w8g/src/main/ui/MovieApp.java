package ui;

import model.*;
import persistence.AlreadyWLJsonReader;
import persistence.AlreadyWLJsonWriter;
import persistence.PlanToWLJsonReader;
import persistence.PlanToWLJsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

//Contains the code that displays and processes inputs.
//CITATIONS: The JsonSerializationDemo was used as a reference for the fields and constructor.
//LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//Teller App was used as a reference for Main, the method MovieApp, runApp, the display methods, and the check methods.
//Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class MovieApp {
    private static final String JSON_STORE_AWL = "./data/alreadyWatchedList.json";
    private static final String JSON_STORE_PWL = "./data/planToWatchList.json";
    Movie movie = new Movie(null, null, 0);
    AlreadyWatchedList alreadyWatchedList = new AlreadyWatchedList();
    PlanToWatchList planToWatchList = new PlanToWatchList();
    private final Scanner input = new Scanner(System.in);
    private final AlreadyWLJsonReader jsonReaderAWL;
    private final AlreadyWLJsonWriter jsonWriterAWL;
    private final PlanToWLJsonReader jsonReaderPWL;
    private final PlanToWLJsonWriter jsonWriterPWL;

    //EFFECTS: Runs the movie application
    public MovieApp() throws FileNotFoundException {
        jsonReaderAWL = new AlreadyWLJsonReader(JSON_STORE_AWL);
        jsonWriterAWL = new AlreadyWLJsonWriter(JSON_STORE_AWL);
        jsonReaderPWL = new PlanToWLJsonReader(JSON_STORE_PWL);
        jsonWriterPWL = new PlanToWLJsonWriter(JSON_STORE_PWL);
        runApp();
    }

    //MODIFIES: this
    //EFFECTS: processes the users input
    private void runApp() {
        boolean stayOn = true;
        String command = null;

        while (stayOn) {
            displayMain();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {

                for (Event event : EventLog.getInstance()) {
                    System.out.println(event);
                }

                stayOn = false;
            } else {
                checkCommand(command);
            }
        }

        System.out.println("\nCome Again!!!");
    }

    //MODIFIES: this
    //EFFECTS: runs function based on given input
    private void checkCommand(String command) {
        if (command.equals("awl")) {
            displayAlreadyWatchedMenu();
        } else if (command.equals("pwl")) {
            displayPlanToWatchedMenu();
        }
    }

    //MODIFIES: this
    //EFFECTS: runs functions based on given input
    private void alreadyWatchedCommand(String command) {
        String genre = null;

        if (command.equals("am")) {
            addAMovieAlreadyWatched();
        } else if (command.equals("rm")) {
            removeAMovieAlreadyWatched();
        } else if (command.equals("g")) {
            System.out.println("Which genre enter 'hg' for horror, 'ag' for action, 'rg' for romance");
            genre = input.next();
            genreMovie(genre, alreadyWatchedList.getAlreadyWatchedList());
        }  else if (command.equals("awm")) {
            printMoviesAlreadyWatched();
        } else if (command.equals("howmany")) {
            System.out.println("You've watched " + alreadyWatchedList.length() + " movies");
        } else if (command.equals("cr")) {
            changeRating();
        } else if (command.equals("l")) {
            loadAlreadyWatchedList();
        } else if (command.equals("s")) {
            saveAlreadyWatchedList();
        }
    }

    //CITATIONS: The JsonSerializationDemo was used as a reference for this method.
    //LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: saves AlreadyWatchedList to file
    private void saveAlreadyWatchedList() {
        try {
            jsonWriterAWL.openAWL();
            jsonWriterAWL.writeAWL(alreadyWatchedList);
            jsonWriterAWL.closeAWL();
            System.out.println("Your already watched list has been saved to" + JSON_STORE_AWL);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save file to" + JSON_STORE_AWL);
        }
    }

    //MODIFIES: this
    //EFFECTS: Changes the rating of the given movie to the given rating
    private void changeRating() {
        System.out.println("Enter the name of the movie that you would like to change the rating of");
        String changedMovie = input.next();
        System.out.println("Enter the new rating you would like for this movie");
        int newRating = Integer.parseInt(input.next());
        alreadyWatchedList.changeRating(changedMovie, newRating);
    }

    //CITATIONS: The JsonSerializationDemo was used as a reference for this method.
    //LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: loads AlreadyWatchedList from file
    private void loadAlreadyWatchedList() {
        try {
            alreadyWatchedList = jsonReaderAWL.read();
            System.out.println("Loaded already watched list from" + JSON_STORE_AWL);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_AWL);
        }
    }


    //MODIFIES: this
    //EFFECTS: runs functions based on given input
    private void planToWatchCommand(String command) {
        String genre = null;

        if (command.equals("am")) {
            addAMoviePlanToWatch();
        } else if (command.equals("rm")) {
            removeAMoviePlanToWatch();
        } else if (command.equals("g")) {
            System.out.println("Which genre enter 'hg' for horror, 'ag' for action, 'rg' for romance");
            genre = input.next();
            genreMovie(genre, planToWatchList.getPlanToWatchList());
        }  else if (command.equals("pwm")) {
            printMoviesPlanToWatch();
        } else if (command.equals("howmany")) {
            System.out.println("You plan to watch " + planToWatchList.length() + " movies");
        } else if (command.equals("l")) {
            loadPlanToWatchList();
        } else if (command.equals("s")) {
            savePlanToWatchList();
        }
    }

    //CITATIONS: The JsonSerializationDemo was used as a reference for this method.
    //LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: saves PlanToWatchList to file
    private void savePlanToWatchList() {
        try {
            jsonWriterPWL.openPWL();
            jsonWriterPWL.writePWL(planToWatchList);
            jsonWriterPWL.closePWL();
            System.out.println("Your plan to watch list has been saved to" + JSON_STORE_PWL);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save file to" + JSON_STORE_PWL);
        }
    }

    //CITATIONS: The JsonSerializationDemo was used as a reference for this method.
    //LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: loads PlanToWatchList from file
    private void loadPlanToWatchList() {
        try {
            planToWatchList = jsonReaderPWL.read();
            System.out.println("Loaded plan to watch list from" + JSON_STORE_PWL);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_PWL);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a movie to the plan to watch list
    private void addAMoviePlanToWatch() {
        System.out.println("Enter the title (instead of spaces use '-')");
        String title = input.next();
        System.out.println("Enter the genre can be Action, Romance, or Horror");
        String genre = input.next();
        genre = genre.toLowerCase();

        movie = new Movie(title, genre, 0);
        planToWatchList.addMovie(movie);
    }

    //MODIFIES: this
    //EFFECTS: removes a movie to the already watched list
    private void removeAMoviePlanToWatch() {
        System.out.println("Enter the title of the movie you wish to remove (instead of spaces use '-')");
        String title = input.next();
        System.out.println("Enter the genre of the movie you wish to remove");
        String genre = input.next();

        movie = new Movie(title, genre, 0);
        planToWatchList.removeMovie(movie);
    }


    //MODIFIES: this
    //EFFECTS: adds a movie to the already watched list
    private void addAMovieAlreadyWatched() {
        System.out.println("Enter the title (instead of spaces use '-')");
        String title = input.next();
        System.out.println("Enter the genre can be Action, Romance, or Horror");
        String genre = input.next();
        genre = genre.toLowerCase();
        System.out.println("Now enter your rating for this movie from 1-10");
        int rating = Integer.parseInt(input.next());

        movie = new Movie(title, genre, rating);
        alreadyWatchedList.addMovie(movie);
    }

    //MODIFIES: this
    //EFFECTS: removes a movie to the already watched list
    private void removeAMovieAlreadyWatched() {
        System.out.println("Enter the title of the movie you wish to remove (instead of spaces use '-')");
        String title = input.next();
        System.out.println("Enter the genre of the movie you wish to remove");
        String genre = input.next();
        System.out.println("Enter the rating of the movie you wish to remove");
        int rating = Integer.parseInt(input.next());

        movie = new Movie(title, genre, rating);
        alreadyWatchedList.removeMovie(movie);
    }

    //EFFECTS: Prints out all movies in the list
    private void printMoviesAlreadyWatched() {
        System.out.println("You've watched: ");
        for (Movie movie : alreadyWatchedList.getAlreadyWatchedList()) {
            System.out.println(movie.getName() + " " + movie.getGenre() + " " + movie.getRating() + "/10");
        }
    }

    //EFFECTS: Prints out all movies in the list
    private void printMoviesPlanToWatch() {
        System.out.println("You plan to watch: ");
        for (Movie movie : planToWatchList.getPlanToWatchList()) {
            System.out.println(movie.getName() + " " + movie.getGenre() + " " + movie.getRating() + "/NA");
        }
    }

    //EFFECTS: runs functions based on given input
    private void genreMovie(String genre, ArrayList<Movie> movieArrayList) {
        if (Objects.equals(genre, "ag")) {
            allActionMovies(movieArrayList);
        } else if (Objects.equals(genre, "rg")) {
            allRomanceMovies(movieArrayList);
        } else if (Objects.equals(genre, "hg")) {
            allHorrorMovies(movieArrayList);
        }
    }

    //EFFECTS: prints out all movies with the genre "horror"
    private void allHorrorMovies(ArrayList<Movie> movieArrayList) {
        for (Movie movie : movieArrayList) {
            if (Objects.equals(movie.getGenre(), "horror")) {
                System.out.println(movie.getName() + " " + movie.getGenre() + " " + movie.getRating());
            }
        }
    }

    //EFFECTS: prints out all movies with the genre "action"
    private void allActionMovies(ArrayList<Movie> movieArrayList) {
        for (Movie movie : movieArrayList) {
            if (Objects.equals(movie.getGenre(), "action")) {
                System.out.println(movie.getName() + " " + movie.getGenre() + " " + movie.getRating());
            }
        }
    }

    //EFFECTS: prints out all movies with the genre "romance"
    private void allRomanceMovies(ArrayList<Movie> movieArrayList) {
        for (Movie movie : movieArrayList) {
            if (Objects.equals(movie.getGenre(), "romance")) {
                System.out.println(movie.getName() + " " + movie.getGenre() + " " + movie.getRating());
            }
        }
    }

    //EFFECTS: Displays options on main menu to user
    private void displayMain() {
        System.out.println("\nWhat would you like to edit:");
        System.out.println("\tawl -> Already Watched List");
        System.out.println("\tpwl -> Plan to Watch List");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: Displays options on already watched menu to user
    private void displayAlreadyWatchedMenu() {
        String command = null;

        System.out.println("\nWhat would you like to edit on the already watched list:");
        System.out.println("\tam -> Add a movie");
        System.out.println("\trm -> Remove a movie");
        System.out.println("\tg -> Find movies in a certain genre");
        System.out.println("\tawm -> See the movies you've already watched");
        System.out.println("\thowMany -> See how many movies you have watched");
        System.out.println("\tcr -> Change the rating of a movie you have watched");
        System.out.println("\ts -> save already watched list to file");
        System.out.println("\tl -> load already watched list from file");

        command = input.next();
        command = command.toLowerCase();

        alreadyWatchedCommand(command);
    }

    //EFFECTS: Displays options on plan to watch menu to user
    private void displayPlanToWatchedMenu() {
        String command = null;

        System.out.println("\nWhat would you like to edit on the plan to watch list:");
        System.out.println("\tam -> Add a movie");
        System.out.println("\trm -> Remove a movie");
        System.out.println("\tg -> Find movies in a certain genre");
        System.out.println("\tpwm -> See the movies you plan to watch");
        System.out.println("\thowMany -> See how many movies you plan to watch");
        System.out.println("\ts -> save plan to watch list to file");
        System.out.println("\tl -> load plan to watch list from file");

        command = input.next();
        command = command.toLowerCase();

        planToWatchCommand(command);
    }
}
