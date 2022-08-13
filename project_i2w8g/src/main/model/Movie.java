package model;

import org.json.JSONObject;
import persistence.Writable;

//Class creates movies objects and contains getters and setters for the objects
public class Movie implements Writable {

    String name;
    String genre;
    int rating;

    //REQUIRES: rating must be an int between 0 and 10, 0 and 10 included. Genre can only be "action", "horror",
    // and "romance", Spaces must not be used in the name.
    //EFFECTS: Constructs a new Movie.
    public Movie(String name, String genre, int rating) {

        this.name = name;
        this.genre = genre;
        this.rating = rating;
    }

    //EFFECTS: Returns name of this
    public String getName() {
        return this.name;
    }

    //EFFECTS: Returns genre of this
    public String getGenre() {
        return this.genre;
    }

    //EFFECTS: Returns rating of this
    public int getRating() {
        return this.rating;
    }

    //EFFECTS: sets rating of this to given int
    public void setRating(int rating) {
        this.rating = rating;
    }

    //CITATIONS: The JsonSerializationDemo was used as a reference for this method.
    //LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //EFFECTS: Coverts a movie to a JSONObject
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("genre", genre);
        String convertedRating = Integer.toString(rating);
        json.put("rating", convertedRating);
        return json;
    }
}
