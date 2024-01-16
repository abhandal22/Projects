package persistence;

import org.json.JSONObject;

//CITATIONS: The JsonSerializationDemo was used as a reference for this class.
//LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//Interfaces a method that's used in AlreadyWatchedList, PlanToWatchList, and Movie
public interface Writable {
    //EFFECTS: turns this into a JSON object and returns it
    JSONObject convertToJson();
}
