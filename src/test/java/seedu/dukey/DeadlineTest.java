package seedu.dukey;
import Exceptions.DukeyException;
import Tasks.DeadLine;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    public void initialisationTest() throws DukeyException {
        DeadLine dummy = new DeadLine("return book /by 01/02/2025 1400", false);
        assertEquals("return book",dummy.text);

        String input = "01/02/2025 1400";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        assertEquals(dateTime.getDayOfMonth(), dummy.dueDate.getDayOfMonth());

    }
    @Test
    void testDeadlineInitializationThrows() {
        DukeyException exception = assertThrows(DukeyException.class,
                () -> new DeadLine("", true)); // the code you expect to fail
        assertEquals("\"Description Missing!\"", exception.getMessage());
    }

    @Test
    public void anotherDummyTest(){
            assertEquals(4, 4);
        }
}
