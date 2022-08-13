package persistence;

import model.Movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
//CITATIONS: The JsonSerializationDemo was used as a reference for the method in this class.
//LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//Class contains the code to check if the information in the json file is the same as the given movie

public class WatchListJsonTest {

    //EFFECTS: Checks if the information found in the json file matches the given movie
    protected void checkMovie(String name, String genre, String rating, Movie movie) {
        assertEquals(name, movie.getName());
        assertEquals(genre, movie.getGenre());
        assertEquals(Integer.parseInt(rating), movie.getRating());
    }
}
