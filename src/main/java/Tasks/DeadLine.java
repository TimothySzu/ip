package Tasks;

import Exceptions.DukeyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLine extends Task {

    public LocalDateTime dueDate;


    public DeadLine (String text, boolean isMarked) throws DukeyException {
        super();
        this.isMarked = isMarked;
        this.type = "D";

        String [] temp = text.split(" ");
        String deadlineText = "";
        String deadlineDate = "";
        StringBuilder deadlineDesc = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].equals("/by")) {
                deadlineText = deadlineDesc.toString();  // Save description before /by
                deadlineDesc = new StringBuilder();
            } else {
                deadlineDesc.append(temp[i]).append(" ");
            }
        }
        if (deadlineText.isEmpty()) {
            throw new DukeyException("Description Missing!");  // Handle missing description
        }
        this.text = deadlineText.trim();
        this.dueDate = convertToDateTime (deadlineDesc.toString().trim());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String temp1 = dueDate.format(formatter);
        return super.toString() + " (by: " + temp1 + ")";
    }
    @Override
    public String toTxt () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String temp1 = dueDate.format(formatter);
        return super.toString() + " /by " + temp1;
    }
}
