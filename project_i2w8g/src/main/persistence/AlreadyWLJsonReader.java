package persistence;

import model.AlreadyWatchedList;
import model.Movie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
//CITATIONS: The JsonSerializationDemo was used as a reference for all methods in this class.
//LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads AlreadyWatchedList from JSON data stored in file
public class AlreadyWLJsonReader {
    private String source;

    //EFFECTS: Creates a reader to read from the source file
    public AlreadyWLJsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads AlreadyWatchedList from file and returns it
    public AlreadyWatchedList read() throws IOException {
        String awlJsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(awlJsonData);
        //logEventLoad();
        return parseAlreadyWatchedList(jsonObject);
    }

    //EFFECTS: Reads the JSON file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses AlreadyWatchedList from JSON object and returns it
    private AlreadyWatchedList parseAlreadyWatchedList(JSONObject jsonObject) {
        AlreadyWatchedList awl = new AlreadyWatchedList();
        //EventLog.getInstance().logEvent(new Event("Already watched list was loaded"));
        awl.logEventLoad();
        addMovies(awl, jsonObject);
        return awl;
    }

    //MODIFIES: awl
    //EFFECTS: parses movies from the JSON object and adds them to AlreadyWatchedList
    private void addMovies(AlreadyWatchedList awl, JSONObject jsonObject) {
        JSONArray jsonArrayAWL = jsonObject.getJSONArray("movies");
        for (Object json : jsonArrayAWL) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(awl, nextMovie);
        }
    }

    //MODIFIES: awl
    //EFFECTS: parses movie from the JSON object and adds it to AlreadyWatchedList
    private void addMovie(AlreadyWatchedList awl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String genre = jsonObject.getString("genre");
        String rating = jsonObject.getString("rating");
        int convertedRating = Integer.parseInt(rating);
        Movie movie = new Movie(name, genre, convertedRating);
        awl.addMovie(movie);
    }
}
