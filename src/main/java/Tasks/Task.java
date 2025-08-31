package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Task to abstract the common task behaviours */
public class Task {

    /** Whether a tasked is marked as completed or not, not completed by default*/
    protected boolean isMarked  =  false;
    /** Type of Task, "T", "D", "E"/ ToDo, DeadLine, Event */
    public String type;
    /** Description of task */
    public String text;


    public void mark () {
        isMarked = true;
        System.out.println("________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        System.out.println("________________________________");

    }

    public void unmark () {
        isMarked = false;
        System.out.println("________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
        System.out.println("________________________________");
    }

    /**
     * Converts a string in the format "dd/MM/yyyy HHmm" into a {@code LocalDateTime}.
     *
     * @param date the date-time string to parse, in the format "dd/MM/yyyy HHmm".
     * @return a {@code LocalDateTime} representing the parsed date and time.
     */
    LocalDateTime convertToDateTime (String date) {
        //date format : dd/mm//yyyy 0000
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dt = LocalDateTime.parse(date, formatter);
        return dt;
    }

    @Override
    public String toString() {
        if (isMarked) {
            return "[" + type + "]" + "[X] " + text;
        } else {
            return "[" + type + "]" + "[ ] " + text;
        }
    }

    /**
     * Converts task description into the appropriate format to write into .txt file.
     *
     * @return The appropriate format to write into .txt file.
     */
    public String toTxt() {
        return this.toString();
    }
}
