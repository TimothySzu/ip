package Tasks;

import Exceptions.DukeyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Represents DeadLine task */
public class DeadLine extends Task {

    /** Deadline of task */
    public LocalDateTime dueDate;

    /**
     * Initialises a DeadLine task.
     * Parses the user input to find the dueDate.
     *
     * @param text the description of task.
     * @param isMarked whether the task is marked as completed.
     * @throws DukeyException if no task description provided.
     */
    public DeadLine (String text, boolean isMarked) throws DukeyException {
        super();

        //set the isMarked status and type
        this.isMarked = isMarked;
        this.type = "D";

        //split user input by whitespace and put substrings into an array
        String [] temp = text.split(" ");

        //temp variable to store task description
        String deadlineText = "";
        //temp variable to store task deadline
        String deadlineDate = "";

        //use StringBuilder to append strings efficiently
        StringBuilder deadlineDesc = new StringBuilder();

        //loop through substring array
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].equals("/by")) {
                deadlineText = deadlineDesc.toString();  // Save task description before /by
                deadlineDesc = new StringBuilder(); //reset deadlineDesc
            } else {
                deadlineDesc.append(temp[i]).append(" "); //Append substring and a whitespace
            }
        }
        //throw DukeyException if task description missing
        if (deadlineText.isEmpty()) {
            throw new DukeyException("Description Missing!");  // Handle missing description
        }

        //set the task description and deadline
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
