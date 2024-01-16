package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    Movie movie1;
    Movie movie2;

    @BeforeEach
    public void setUp(){
        movie1 = new Movie("", "", 0);
        movie2 = new Movie("beep", "yeet", 3);
    }

    @Test
    public void testMovieConstructor(){
        assertEquals("", movie1.getName());
        assertEquals("", movie1.getGenre());
        assertEquals(0, movie1.getRating());
    }

    @Test
    public void testMovieConstructorWithStuff(){
        assertEquals("beep", movie2.getName());
        assertEquals("yeet", movie2.getGenre());
        assertEquals(3, movie2.getRating());
    }

    @Test
    public void testGetName(){
        assertEquals("beep", movie2.getName());
    }

    @Test
    public void testGetGenre(){
        assertEquals("yeet", movie2.getGenre());
    }

    @Test
    public void testGetRating(){
        assertEquals(3, movie2.getRating());
    }

    @Test
    public void testSetRating() {
        assertEquals(0, movie1.getRating());
        movie1.setRating(4);
        assertEquals(4, movie1.getRating());
    }

    @Test
    public void testConvertToJson() {
        JSONObject convertedM2 = movie2.convertToJson();
        String convertedM2String = convertedM2.toString();

        String movie2String = "{\"name\":\"beep\",\"genre\":\"yeet\",\"rating\":\"3\"}";

        assertEquals(movie2String, convertedM2String);
    }
}