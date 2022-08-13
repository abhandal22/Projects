package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AlreadyWatchedListTest {

    AlreadyWatchedList awl1;
    PlanToWatchList pwl1;
    Movie movie1;
    Movie movie2;

    @BeforeEach
    public void setUp() {
        awl1 = new AlreadyWatchedList();
        pwl1 = new PlanToWatchList();
        movie1 = new Movie("", "", 0);
        movie2 = new Movie("beep", "yeet", 3);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, awl1.length());
    }

    @Test
    public void testAddMovieOne() {
        //awl1.addMovie(movie1);
        assertTrue(awl1.addMovie(movie1));
        assertEquals(1, awl1.length());
    }

    @Test
    public void testAddMovieMulti() {
        assertEquals(0, awl1.length());
        assertTrue(awl1.addMovie(movie1));
        assertEquals(1, awl1.length());
        assertTrue(awl1.addMovie(movie2));
        assertEquals(2, awl1.length());
    }

    @Test
    public void testAddMovieDuplicate() {
        //awl1.addMovie(movie2);
        assertTrue(awl1.addMovie(movie2));
        assertEquals(1, awl1.length());
        //assertTrue(awl1.addMovie(movie2));
        awl1.addMovie(movie2);
        assertEquals(1, awl1.length());
        assertFalse(awl1.addMovie(movie2));
    }


    @Test
    public void testRemoveMovieEmpty() {
        assertEquals(0, awl1.length());
        assertFalse(awl1.removeMovie(movie1));
        assertEquals(0, awl1.length());
    }

    @Test
    public void testRemoveMovieHasOne() {
        awl1.addMovie(movie1);
        assertEquals(1, awl1.length());
        assertTrue(awl1.removeMovie(movie1));
        assertEquals(0, awl1.length());
    }

    @Test
    public void testRemoveMovieWrongOne() {
        awl1.addMovie(movie1);
        assertEquals(1, awl1.length());
        assertFalse(awl1.removeMovie(movie2));
        assertEquals(1, awl1.length());
    }

    @Test
    public void testRemoveMovieMulti() {
        awl1.addMovie(movie1);
        awl1.addMovie(movie2);
        assertEquals(2, awl1.length());
        assertTrue(awl1.removeMovie(movie1));
        assertEquals(1, awl1.length());
        assertTrue(awl1.removeMovie(movie2));
        assertEquals(0, awl1.length());
    }

    @Test
    public void testLengthEmpty() {
        assertEquals(0, awl1.length());
    }

    @Test
    public void testLengthMulti() {
        awl1.addMovie(movie1);
        awl1.addMovie(movie2);
        assertEquals(2, awl1.length());
    }

    @Test
    public void testAlreadyThereItIs() {
        awl1.addMovie(movie1);
        assertTrue(awl1.alreadyThere(movie1));
    }

    @Test
    public void testAlreadyThereItIsnt() {
        awl1.addMovie(movie1);
        assertFalse(awl1.alreadyThere(movie2));
    }

    @Test
    public void testAlreadyThereItIsMulti() {
        awl1.addMovie(movie2);
        awl1.addMovie(movie1);
        assertTrue(awl1.alreadyThere(movie2));
    }

    @Test
    public void testChangeRating() {
        awl1.addMovie(movie1);
        assertEquals(0, movie1.getRating());
        awl1.changeRating(movie1.getName(), 7);
        assertEquals(7, movie1.getRating());
    }

    @Test
    public void testChangeRatingMissing() {
        awl1.addMovie(movie1);
        assertEquals(0, movie1.getRating());
        assertFalse(awl1.changeRating(movie2.getName(), 7));
        assertEquals(0, movie1.getRating());
    }


    @Test
    public void testActionGenre() {
        Movie actionMovie = new Movie("Die Hard", "action", 8);
        assertFalse(awl1.actionGenre(movie1));
        assertFalse(awl1.actionGenre(movie2));
        assertTrue(awl1.actionGenre(actionMovie));
    }

    @Test
    public void testHorrorGenre() {
        Movie horrorMovie = new Movie("Halloween", "horror", 8);
        assertFalse(awl1.horrorGenre(movie1));
        assertFalse(awl1.horrorGenre(movie2));
        assertTrue(awl1.horrorGenre(horrorMovie));
    }

    @Test
    public void testRomanceGenre() {
        Movie romanceMovie = new Movie("Your Name", "romance", 8);
        assertFalse(awl1.romanceGenre(movie1));
        assertFalse(awl1.romanceGenre(movie2));
        assertTrue(awl1.romanceGenre(romanceMovie));
    }

    @Test
    public void testGetAlreadyWatchedList() {
        awl1.addMovie(movie2);
        awl1.addMovie(movie1);
        assertEquals(2, awl1.getAlreadyWatchedList().size());
    }

    @Test
    public void testConvertToJsonEmpty() {

        JSONObject convertedAWL1Json = awl1.convertToJson();
        String convertedAWL1String = convertedAWL1Json.toString();

        String expectedAWL1Json = "{\"movies\":[]}";

        assertEquals(expectedAWL1Json, convertedAWL1String);
    }

    @Test
    public void testConvertToJsonGeneral() {
        awl1.addMovie(movie1);
        awl1.addMovie(movie2);

        JSONObject convertedAWL1Json = awl1.convertToJson();
        String convertedAWL1String = convertedAWL1Json.toString();

        String expectedAWL1Json = "{\"movies\":[{\"name\":\"\",\"genre\":\"\",\"rating\":\"0\"}," +
                "{\"name\":\"beep\",\"genre\":\"yeet\",\"rating\":\"3\"}]}";

        assertEquals(expectedAWL1Json, convertedAWL1String);
    }

}
