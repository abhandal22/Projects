package model;

//Interfaces methods that are used by both the AlreadyWatchedList class and the PlanToWatchList class
public interface MovieList {

    //MODIFIES: this
    //EFFECTS: adds this to the list
    Boolean addMovie(Movie m);

    //MODIFIES: this
    //EFFECTS: Removes this from list
    Boolean removeMovie(Movie movie);

    //EFFECTS: Returns length of this
    int length();

    //EFFECTS: Checks if the given movies is in the list
    Boolean alreadyThere(Movie movie);

    //EFFECTS: Checks if the given movie is in the action genre
    Boolean actionGenre(Movie movie);

    //EFFECTS: Checks if the given movie is in the horror genre
    Boolean horrorGenre(Movie movie);

    //EFFECTS: Checks if the given movie is in the romance genre
    Boolean romanceGenre(Movie movie);
}
