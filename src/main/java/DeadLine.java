public class DeadLine extends Task {

    String dueDate;

    public DeadLine (String text, String dueDate) {
        super(text);
        this.dueDate = dueDate;
        this.type = "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDate + ")";
    }
}
