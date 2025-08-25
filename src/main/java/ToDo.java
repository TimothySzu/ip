public class ToDo extends Task {

    public ToDo (String text, boolean isMarked) throws DukeyException {
        super();

        this.type = "T";
        this.isMarked = isMarked;

        if (text.isEmpty()) {
            throw new DukeyException("Description Missing!");  // Handle missing description
        } else {
            this.text = text;
        }
    }
}
