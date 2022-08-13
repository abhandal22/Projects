package persistence;

import model.PlanToWatchList;
import model.Movie;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
//CITATIONS: The JsonSerializationDemo was used as a reference for all tests in this class.
//LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class PlanToWLJsonReaderTest extends WatchListJsonTest{

    @Test
    void testPlanToWLReaderNonExistentFile() {
        PlanToWLJsonReader reader = new PlanToWLJsonReader("./data/notReal.json");
        try {
            PlanToWatchList pwl = reader.read();
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            //expected result
        }
    }

    @Test
    void testAlreadyWLEmptyAWL() {
        PlanToWLJsonReader reader = new PlanToWLJsonReader("./data/testReaderEmptyPlanToWatchList.json");
        try {
            PlanToWatchList pwl = reader.read();
            assertEquals(0, pwl.length());
        } catch (IOException e) {
            fail("Failed to read from file");
        }
    }

    @Test
    void testAlreadyWLGeneralAWL() {
        PlanToWLJsonReader reader = new PlanToWLJsonReader("./data/testReaderGeneralAlreadyWatchedList.json");
        try {
            PlanToWatchList pwl = reader.read();
            ArrayList<Movie> movies = pwl.getPlanToWatchList();
            assertEquals(3, movies.size());
            checkMovie("Die-Hard", "action", "8", movies.get(0));
            checkMovie("Your-Name", "romance", "5", movies.get(1));
            checkMovie("Halloween", "horror", "10", movies.get(2));
        } catch (IOException e) {
            fail("Failed to read from file");
        }
    }
}
