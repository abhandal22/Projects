package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PlanToWatchListTest {

    PlanToWatchList pwl1;
    AlreadyWatchedList awl1;
    Movie movie1;
    Movie movie2;

    @BeforeEach
    public void setUp() {
        pwl1 = new PlanToWatchList();
        awl1 = new AlreadyWatchedList();
        movie1 = new Movie("", "", 0);
        movie2 = new Movie("beep", "yeet", 7);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, pwl1.length());
    }

    @Test
    public void testAddMovieOne() {
        assertTrue(pwl1.addMovie(movie1));
        assertEquals(1, pwl1.length());
    }

    @Test
    public void testAddMovieMulti() {
        assertEquals(0, pwl1.length());
        assertTrue(pwl1.addMovie(movie1));
        assertEquals(1, pwl1.length());
        assertTrue(pwl1.addMovie(movie2));
        assertEquals(2, pwl1.length());
    }

    @Test
    public void testAddMovieDuplicate() {
        assertTrue(pwl1.addMovie(movie2));
        assertEquals(1, pwl1.length());
        assertFalse(pwl1.addMovie(movie2));
        assertEquals(1, pwl1.length());
    }

    @Test
    public void testAddMovieInListAndInWatched() {
        pwl1.addMovie(movie1);
        awl1.addMovie(movie1);
        assertFalse(pwl1.addMovie(movie1));
    }

    @Test
    public void testRemoveMovieEmpty() {
        assertEquals(0, pwl1.length());
        assertFalse(pwl1.removeMovie(movie1));
        assertEquals(0, pwl1.length());
    }

    @Test
    public void testRemoveMovieOne() {
        pwl1.addMovie(movie2);
        assertEquals(1, pwl1.length());
        assertTrue(pwl1.removeMovie(movie2));
        assertEquals(0, pwl1.length());
    }

    @Test
    public void testRemoveMovieWrongOne() {
        pwl1.addMovie(movie1);
        assertEquals(1, pwl1.length());
        assertFalse(pwl1.removeMovie(movie2));
        assertEquals(1, pwl1.length());
    }

    @Test
    public void testRemoveMovieMulti() {
        pwl1.addMovie(movie1);
        pwl1.addMovie(movie2);
        assertEquals(2, pwl1.length());
        assertTrue(pwl1.removeMovie(movie1));
        assertEquals(1, pwl1.length());
        assertTrue(pwl1.removeMovie(movie2));
        assertEquals(0, pwl1.length());
    }

    @Test
    public void testLengthEmpty() {
        assertEquals(0, pwl1.length());
    }

    @Test
    public void testLengthMulti() {
        pwl1.addMovie(movie2);
        pwl1.addMovie(movie1);
        assertEquals(2, pwl1.length());
    }

    @Test
    public void testAlreadyThereItIs() {
        pwl1.addMovie(movie2);
        assertTrue(pwl1.alreadyThere(movie2));
    }

    @Test
    public void testAlreadyThereItIsnt() {
        pwl1.addMovie(movie1);
        assertFalse(pwl1.alreadyThere(movie2));
    }

    @Test
    public void testAlreadyThereMulti() {
        pwl1.addMovie(movie1);
        pwl1.addMovie(movie2);
        assertTrue(pwl1.alreadyThere(movie2));
        assertTrue(pwl1.alreadyThere(movie1));
    }

//    @Test
//    public void testAlreadyWatchedNot1() {
//        assertFalse(pwl1.alreadyWatched(movie1));
//    }
//
//    @Test
//    public void testAlreadyWatchedNot2() {
//        awl1.addMovie(movie2);
//        assertFalse(pwl1.alreadyWatched(movie1));
//    }
//
//    @Test
//    public void testAlreadyWatchedHave() {
//        awl1.addMovie(movie2);
//        assertTrue(awl1.alreadyWatched(movie2));
//    }
//
//    @Test
//    public void testAlreadyWatchedMulti() {
//        awl1.addMovie(movie1);
//        awl1.addMovie(movie2);
//        assertEquals(2, awl1.length());
//        assertTrue(awl1.alreadyWatched(movie1));
//    }

    @Test
    public void testActionGenre() {
        Movie actionMovie = new Movie("Die Hard", "action", 8);
        assertFalse(pwl1.actionGenre(movie1));
        assertFalse(pwl1.actionGenre(movie2));
        assertTrue(pwl1.actionGenre(actionMovie));
    }

    @Test
    public void testHorrorGenre() {
        Movie horrorMovie = new Movie("Halloween", "horror", 8);
        assertFalse(pwl1.horrorGenre(movie1));
        assertFalse(pwl1.horrorGenre(movie2));
        assertTrue(pwl1.horrorGenre(horrorMovie));
    }

    @Test
    public void testRomanceGenre() {
        Movie romanceMovie = new Movie("Your Name", "romance", 8);
        assertFalse(pwl1.romanceGenre(movie1));
        assertFalse(pwl1.romanceGenre(movie2));
        assertTrue(pwl1.romanceGenre(romanceMovie));
    }

    @Test
    public void testGetPlanToWatchList() {
        pwl1.addMovie(movie2);
        pwl1.addMovie(movie1);
        assertEquals(2, pwl1.getPlanToWatchList().size());
    }

    @Test
    public void testConvertToJsonEmpty() {

        JSONObject convertedPWL1Json = pwl1.convertToJson();
        String convertedPWL1String = convertedPWL1Json.toString();

        String expectedPWL1Json = "{\"movies\":[]}";

        assertEquals(expectedPWL1Json, convertedPWL1String);
    }

    @Test
    public void testConvertToJsonGeneral() {
        pwl1.addMovie(movie1);
        pwl1.addMovie(movie2);

        JSONObject convertedPWL1Json = pwl1.convertToJson();
        String convertedPWL1String = convertedPWL1Json.toString();

        String expectedPWL1Json = "{\"movies\":[{\"name\":\"\",\"genre\":\"\",\"rating\":\"0\"}," +
                "{\"name\":\"beep\",\"genre\":\"yeet\",\"rating\":\"7\"}]}";

        assertEquals(expectedPWL1Json, convertedPWL1String);
    }

}
