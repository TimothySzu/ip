public class Event extends Task {

    String from;
    String to;

    public Event (String text, String from, String to) {
        super(text);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    @Override
    public String toString () {
        return super.toString() + " (from " + from + " to: " + to + ")";
    }

}
