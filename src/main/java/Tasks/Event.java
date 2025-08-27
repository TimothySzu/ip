package Tasks;

import Exceptions.DukeyException;
import Tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    LocalDateTime from;
    LocalDateTime to;

    public Event (String text, boolean isMarked) throws DukeyException {
        super();
        this.isMarked = isMarked;
        this.type = "E";
        String [] temp = text.split(" ");
        String eventText = "";
        StringBuilder eventDesc = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].equals("/from")) {
                eventText = eventDesc.toString().trim();  // Save description before /from
                eventDesc = new StringBuilder();
            } else if (temp[i].equals("/to")) {
                from = convertToDateTime(eventDesc.toString().trim());  // Save 'from' date/time
                eventDesc = new StringBuilder();
            } else {
                eventDesc.append(temp[i]).append(" ");
            }
        }
        if (eventText.isEmpty()) {
            throw new DukeyException("Description Missing!");  // Handle missing description
        }
        this.text = eventText.trim();
        this.to = convertToDateTime(eventDesc.toString().trim());
    }

    @Override
    public String toString () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String temp1 = from.format(formatter);
        String temp2 = from.format(formatter);
        return super.toString() + " (from " + temp1 + " to: " + temp2 + ")";
    }
    @Override
    public String toTxt () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String temp1 = from.format(formatter);
        String temp2 = from.format(formatter);
        return super.toString() + " /from " + temp1 + " /to " + temp2;
    }

}
