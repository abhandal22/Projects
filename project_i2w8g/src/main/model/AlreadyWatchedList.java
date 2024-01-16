package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

//Contains methods that work on the already watched list and creates the already watched list
public class AlreadyWatchedList implements MovieList, Writable {

    private ArrayList<Movie> alreadyWatchedList;

    //EFFECTS: Creates an empty AlreadyWatchedList
    public AlreadyWatchedList() {
        this.alreadyWatchedList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds movie to this if it's not already there and removes movie from WatchList. If it isn't already there
    // return true. Otherwise, return false
    @Override
    public Boolean addMovie(Movie movie) {
        if (this.alreadyThere(movie)) {
            return false;
        } else {
            alreadyWatchedList.add(movie);
            EventLog.getInstance().logEvent(new Event("A movie was added to already watched list"));
            return true;
        }
    }

    //MODIFIES: this
    //EFFECTS: removes movie from this and returns true if it was there. Otherwise, it returns false.
    @Override
    public Boolean removeMovie(Movie movie) {
        for (Movie movie1 : alreadyWatchedList) {
            if (Objects.equals(movie1.getName(), movie.getName())) {
                alreadyWatchedList.remove(movie1);
                EventLog.getInstance().logEvent(new Event("A movie was removed form already watched list"));
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns length of this
    @Override
    public int length() {
        EventLog.getInstance().logEvent(new Event("Length of already watched list was returned"));
        return alreadyWatchedList.size();
    }

    //EFFECTS: Returns true if the given movie name is already in this, false otherwise.
    @Override
    public Boolean alreadyThere(Movie movie) {
        for (Movie movie1 : alreadyWatchedList) {
            if (Objects.equals(movie.getName(), movie1.getName())) {
                return true;
            }
        }
        return false;
    }

    //REQUIRES: int must be between 1 and 10 including 1 and 10.
    //MODIFIES: this
    //EFFECTS: Changes rating of given movie to given int. If given movie is found returns true, if it isn't found
    // returns false.
    public Boolean changeRating(String title, int rating) {
        for (Movie movie : alreadyWatchedList) {
            if (title.equals(movie.getName())) {
                movie.setRating(rating);
                EventLog.getInstance().logEvent(new Event("A movies rating was changed"));
                return true;
            }
        }
        return false;
    }

    //EFFECTS: Returns a movie if its genre is "action"
    @Override
    public Boolean actionGenre(Movie movie) {
        EventLog.getInstance().logEvent(new Event("Action movies in the already watched list were displayed"));
        return movie.getGenre().equals("action");
    }

    //EFFECTS: Returns a movie if its genre is "horror"
    @Override
    public Boolean horrorGenre(Movie movie) {
        EventLog.getInstance().logEvent(new Event("Horror movies in the already watched list were displayed"));
        return movie.getGenre().equals("horror");
    }

    //EFFECTS: Returns a movie if its genre is "romance"
    @Override
    public Boolean romanceGenre(Movie movie) {
        EventLog.getInstance().logEvent(new Event("Romance movies in the already watched list were displayed"));
        return movie.getGenre().equals("romance");
    }

    //EFFECTS: returns the already watched list
    public ArrayList<Movie> getAlreadyWatchedList() {
        EventLog.getInstance().logEvent(new Event("Already watched list was displayed"));
        return alreadyWatchedList;
    }

    //EFFECTS: Creates an event and logs it to the event log
    public void logEventLoad() {
        EventLog.getInstance().logEvent(new Event("Already watched list was loaded"));
    }

    //CITATIONS: The JsonSerializationDemo was used as a reference for this method.
    //LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //EFFECTS: Coverts the already watched list to a JSONObject
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("movies", moviesToJson());
        EventLog.getInstance().logEvent(new Event("Already watched list was saved"));
        return json;
    }

    //CITATIONS: The JsonSerializationDemo was used as a reference for this method.
    //LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //EFFECTS: Coverts all movies in the already watched list to a JSONObject
    private JSONArray moviesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie m : alreadyWatchedList) {
            jsonArray.put(m.convertToJson());
        }

        return jsonArray;
    }
}
