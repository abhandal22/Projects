package persistence;

import model.Movie;
import model.PlanToWatchList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//The JsonSerializationDemo was used as a reference for these tests
//LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class PlanToWLJsonWriterTest extends WatchListJsonTest {

    @Test
    void testPlanToWLJsonWriterInvalidFile() {
        try {
            PlanToWatchList pwl = new PlanToWatchList();
            PlanToWLJsonWriter writerPWL = new PlanToWLJsonWriter("./data/not\0Val:id.json");
            writerPWL.openPWL();
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            //Expected result
        }
    }

    @Test
    void testPlanToWLJsonWriterEmpty() {
        try {
            PlanToWatchList pwl = new PlanToWatchList();
            PlanToWLJsonWriter writerPWL =
                    new PlanToWLJsonWriter("./data/testWriterEmptyPlanToWatchList.json");
            writerPWL.openPWL();
            writerPWL.writePWL(pwl);
            writerPWL.closePWL();

            PlanToWLJsonReader readerPWL =
                    new PlanToWLJsonReader("./data/testWriterEmptyPlanToWatchList.json");
            pwl = readerPWL.read();
            assertEquals(0, pwl.length());
        } catch (IOException e) {
            fail("Was not expecting an exception");
        }
    }

    @Test
    void testPlanToWLJsonWriterGeneral() {
        try {
            PlanToWatchList pwl = new PlanToWatchList();
            Movie m1 = new Movie("Die-Hard", "action", 0);
            Movie m2 = new Movie("Spiral", "horror", 0);
            pwl.addMovie(m1);
            pwl.addMovie(m2);
            PlanToWLJsonWriter writerPWL = new PlanToWLJsonWriter("./data/testWriterGeneralPlanToWatchList.json");
            writerPWL.openPWL();
            writerPWL.writePWL(pwl);
            writerPWL.closePWL();

            PlanToWLJsonReader readerPWL = new PlanToWLJsonReader("./data/testWriterGeneralPlanToWatchList.json");
            pwl = readerPWL.read();
            ArrayList<Movie> movies = pwl.getPlanToWatchList();
            assertEquals(2, pwl.length());
            checkMovie("Die-Hard", "action", "0", m1);
            checkMovie("Spiral", "horror", "0", m2);

        } catch (IOException e) {
            fail("Exception was not expected");
        }
    }
}
