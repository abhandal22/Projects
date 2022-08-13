package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

//Contains methods that work on the plan to watch list and creates the plan to watch list
public class PlanToWatchList implements MovieList, Writable {

    private ArrayList<Movie> planToWatchList;

    //EFFECTS: Creates an empty WatchList
    public PlanToWatchList() {
        this.planToWatchList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds movie to this if not already there, if it isn't already there return true. Otherwise, return false.
    @Override
    public Boolean addMovie(Movie movie) {
        if (this.alreadyThere(movie)) {
            return false;
        } else {
            planToWatchList.add(movie);
            EventLog.getInstance().logEvent(new Event("A movie was added to plan to watch list"));
            return true;
        }
    }


    //MODIFIES: this
    //EFFECTS: removes movie from this. Returns true if it was there, false otherwise
    @Override
    public Boolean removeMovie(Movie movie) {
        for (Movie movie1 : planToWatchList) {
            if (Objects.equals(movie1.getName(), movie.getName())) {
                planToWatchList.remove(movie1);
                EventLog.getInstance().logEvent(new Event("A movie was removed from plan to watch list"));
                return true;
            }
        }
        return false;
    }


    //EFFECTS: returns length of this
    @Override
    public int length() {
        EventLog.getInstance().logEvent(new Event("Length of plan to watch list was returned"));
        return planToWatchList.size();
    }

    //EFFECTS: Returns true if the given movie is already in this.
    @Override
    public Boolean alreadyThere(Movie movie) {
        for (Movie movie1 : planToWatchList) {
            if (Objects.equals(movie.getName(), movie1.getName())) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: Returns a movie if its genre is "action"
    @Override
    public Boolean actionGenre(Movie movie) {
        EventLog.getInstance().logEvent(new Event("Action movies in the plan to watch list were displayed"));
        return movie.getGenre().equals("action");
    }

    //EFFECTS: Returns a movie if its genre is "horror"
    @Override
    public Boolean horrorGenre(Movie movie) {
        EventLog.getInstance().logEvent(new Event("Horror movies in the plan to watch list were displayed"));
        return movie.getGenre().equals("horror");
    }

    //EFFECTS: Returns a movie if its genre is "romance"
    @Override
    public Boolean romanceGenre(Movie movie) {
        EventLog.getInstance().logEvent(new Event("Romance movies in the plan to watch list were displayed"));
        return movie.getGenre().equals("romance");
    }

    //EFFECTS: returns the plan to watch list
    public ArrayList<Movie> getPlanToWatchList() {
        EventLog.getInstance().logEvent(new Event("Plan to watch list was displayed"));
        return planToWatchList;
    }

    //CITATIONS: The JsonSerializationDemo was used as a reference for this method.
    //LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //EFFECTS: Coverts the plan to watch list to a JSONObject
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("movies", moviesToJson());
        EventLog.getInstance().logEvent(new Event("Plan to watch list was saved"));
        return json;
    }

    //CITATIONS: The JsonSerializationDemo was used as a reference for this method.
    //LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //EFFECTS: Coverts all movies in the plan to watch list to a JSONObject
    private JSONArray moviesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie m : planToWatchList) {
            jsonArray.put(m.convertToJson());
        }

        return jsonArray;
    }

    //EFFECTS: Creates an event and logs it to the event log
    public void logEventLoad() {
        EventLog.getInstance().logEvent(new Event("Plan to watch list was loaded"));
    }
}
