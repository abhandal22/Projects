package persistence;

import model.PlanToWatchList;
import model.Movie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
//CITATIONS: The JsonSerializationDemo was used as a reference for all methods in this class.
//LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads PlanToWatchList from JSON data stored in file
public class PlanToWLJsonReader {
    private String source;

    //EFFECTS: Creates a reader to read from the source file
    public PlanToWLJsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads PlanToWatchList from file and returns it
    public PlanToWatchList read() throws IOException {
        String pwlJsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(pwlJsonData);
        //logEventLoad();
        return parsePlanToWatchList(jsonObject);
    }

    //EFFECTS: Reads the JSON file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses PlanToWatchList from JSON object and returns it
    private PlanToWatchList parsePlanToWatchList(JSONObject jsonObject) {
        PlanToWatchList pwl = new PlanToWatchList();
        //EventLog.getInstance().logEvent(new Event("Plan to watch list was loaded"));
        pwl.logEventLoad();
        addMovies(pwl, jsonObject);
        return pwl;
    }

    //MODIFIES: pwl
    //EFFECTS: parses movies from the JSON object and adds them to PlanToWatchList
    private void addMovies(PlanToWatchList pwl, JSONObject jsonObject) {
        JSONArray jsonArrayPWL = jsonObject.getJSONArray("movies");
        for (Object json : jsonArrayPWL) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(pwl, nextMovie);
        }
    }

    //MODIFIES: pwl
    //EFFECTS: parses movie from the JSON object and adds it to PlanToWatchList
    private void addMovie(PlanToWatchList pwl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String genre = jsonObject.getString("genre");
        String rating = jsonObject.getString("rating");
        int convertedRating = Integer.parseInt(rating);
        Movie movie = new Movie(name, genre, convertedRating);
        pwl.addMovie(movie);
    }
}
