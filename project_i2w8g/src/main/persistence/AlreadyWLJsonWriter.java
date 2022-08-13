package persistence;

import model.AlreadyWatchedList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//CITATIONS: The JsonSerializationDemo was used as a reference for this class.
//LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//// Represents a writer that writes AlreadyWatchedList to JSON data and stores it in a file
public class AlreadyWLJsonWriter {
    private static final int TAB = 4;
    private PrintWriter writerAWL;
    private String destination;

    //EFFECTS: constructs the writer to write to the destination file
    public AlreadyWLJsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens the writer, if the file can not be opened throws the FileNotFoundException
    public void openAWL() throws FileNotFoundException {
        writerAWL = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes the JSON representation of the AlreadyWatchedList to file
    public void writeAWL(AlreadyWatchedList awl) {
        JSONObject json = awl.convertToJson();
        saveToFileAWL(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: closes the writer
    public void closeAWL() {
        writerAWL.close();
    }

    //MODIFIES: this
    //EFFECTS: writes the given string to the file
    public void saveToFileAWL(String json) {
        writerAWL.print(json);
    }
}
