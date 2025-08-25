public class DeadLine extends Task {

    String dueDate;

    public DeadLine (String text, boolean isMarked) throws DukeyException {
        super();
        this.isMarked = isMarked;
        this.dueDate = dueDate;
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
        this.dueDate = deadlineDesc.toString().trim();

    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDate + ")";
    }
}
