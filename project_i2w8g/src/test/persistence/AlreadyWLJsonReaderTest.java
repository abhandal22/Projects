package persistence;

import model.AlreadyWatchedList;
import model.Movie;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
//CITATIONS: The JsonSerializationDemo was used as a reference for all tests in this class.
//LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class AlreadyWLJsonReaderTest extends WatchListJsonTest {

    @Test
    void testAlreadyWLReaderNonExistentFile() {
        AlreadyWLJsonReader reader = new AlreadyWLJsonReader("./data/notReal.json");
        try {
            AlreadyWatchedList awl = reader.read();
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            //expected result
        }
    }

    @Test
    void testAlreadyWLEmptyAWL() {
        AlreadyWLJsonReader reader = new AlreadyWLJsonReader("./data/testReaderEmptyAlreadyWatchedList.json");
        try {
            AlreadyWatchedList awl = reader.read();
            assertEquals(0, awl.length());
        } catch (IOException e) {
            fail("Failed to read from file");
        }
    }

    @Test
    void testAlreadyWLGeneralAWL() {
        AlreadyWLJsonReader reader = new AlreadyWLJsonReader("./data/testReaderGeneralAlreadyWatchedList.json");
        try {
            AlreadyWatchedList awl = reader.read();
            ArrayList<Movie> movies = awl.getAlreadyWatchedList();
            assertEquals(3, movies.size());
            checkMovie("Die-Hard", "action", "8", movies.get(0));
            checkMovie("Your-Name", "romance", "5", movies.get(1));
            checkMovie("Halloween", "horror", "10", movies.get(2));
        } catch (IOException e) {
            fail("Failed to read from file");
        }
    }
}
