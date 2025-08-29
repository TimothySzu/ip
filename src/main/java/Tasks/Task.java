package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    protected boolean isMarked  =  false;
    public String type;
    public String text;

    public Task () {
    }

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
    public String toTxt() {
        return this.toString();
    }
}
