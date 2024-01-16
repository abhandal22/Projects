package persistence;

import model.AlreadyWatchedList;
import model.Movie;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//The JsonSerializationDemo was used as a reference for these tests
//LINK: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class AlreadyWLJsonWriterTest extends WatchListJsonTest{

    @Test
    void testAlreadyWLJsonWriterInvalidFile() {
        try {
            AlreadyWatchedList awl = new AlreadyWatchedList();
            AlreadyWLJsonWriter writerAWL = new AlreadyWLJsonWriter("./data/not\0Val:id.json");
            writerAWL.openAWL();
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            //Expected result
        }
    }

    @Test
    void testAlreadyWLJsonWriterEmpty() {
        try {
            AlreadyWatchedList awl = new AlreadyWatchedList();
            AlreadyWLJsonWriter writerAWL =
                    new AlreadyWLJsonWriter("./data/testWriterEmptyAlreadyWatchedList.json");
            writerAWL.openAWL();
            writerAWL.writeAWL(awl);
            writerAWL.closeAWL();

            AlreadyWLJsonReader readerAWL =
                    new AlreadyWLJsonReader("./data/testWriterEmptyAlreadyWatchedList.json");
            awl = readerAWL.read();
            assertEquals(0, awl.length());
        } catch (IOException e) {
            fail("Was not expecting an exception");
        }
    }

    @Test
    void testAlreadyWLJsonWriterGeneral() {
        try {
            AlreadyWatchedList awl = new AlreadyWatchedList();
            Movie m1 = new Movie("Die-Hard", "action", 8);
            Movie m2 = new Movie("Spiral", "horror", 9);
            awl.addMovie(m1);
            awl.addMovie(m2);
            AlreadyWLJsonWriter writerAWL = new AlreadyWLJsonWriter("./data/testWriterGeneralAlreadyWatchedList.json");
            writerAWL.openAWL();
            writerAWL.writeAWL(awl);
            writerAWL.closeAWL();

            AlreadyWLJsonReader readerAWL = new AlreadyWLJsonReader("./data/testWriterGeneralAlreadyWatchedList.json");
            awl = readerAWL.read();
            ArrayList<Movie> movies = awl.getAlreadyWatchedList();
            assertEquals(2, awl.length());
            checkMovie("Die-Hard", "action", "8", m1);
            checkMovie("Spiral", "horror", "9", m2);

        } catch (IOException e) {
            fail("Exception was not expected");
        }
    }
}
