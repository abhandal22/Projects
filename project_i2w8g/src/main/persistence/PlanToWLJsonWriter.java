package persistence;

import model.PlanToWatchList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//CITATIONS: The JsonSerializationDemo was used as a reference for this class.
//LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//// Represents a writer that writes PlanToWatchList to JSON data and stores it in a file
public class PlanToWLJsonWriter {
    private static final int TAB = 4;
    private PrintWriter writerPWL;
    private String destination;

    //EFFECTS: constructs the writer to write to the destination file
    public PlanToWLJsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens the writer, if the file can not be opened throws the FileNotFoundException
    public void openPWL() throws FileNotFoundException {
        writerPWL = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes the JSON representation of the PlanToWatchList to file
    public void writePWL(PlanToWatchList pwl) {
        JSONObject json = pwl.convertToJson();
        saveToFilePWL(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: closes the writer
    public void closePWL() {
        writerPWL.close();
    }

    //MODIFIES: this
    //EFFECTS: writes the given string to the file
    public void saveToFilePWL(String json) {
        writerPWL.print(json);
    }
}
