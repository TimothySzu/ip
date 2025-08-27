public class Event extends Task {

    String from;
    String to;

    public Event (String text, boolean isMarked) throws DukeyException{
        super();
        this.isMarked = isMarked;
        this.type = "E";
        String [] temp = text.split(" ");
        String eventText = "";
        String eventFrom = "";
        String eventTo = "";
        StringBuilder eventDesc = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].equals("/from")) {
                eventText = eventDesc.toString();  // Save description before /from
                eventDesc = new StringBuilder();
            } else if (temp[i].equals("/to")) {
                eventFrom = eventDesc.toString();  // Save 'from' date/time
                eventDesc = new StringBuilder();
            } else {
                eventDesc.append(temp[i]).append(" ");
            }
        }
        if (eventText.isEmpty()) {
            throw new DukeyException("Description Missing!");  // Handle missing description
        }
        this.text = eventText.trim();
        this.from = eventFrom.trim();
        this.to = eventDesc.toString().trim();
    }

    @Override
    public String toString () {
        return super.toString() + " (from " + from + " to: " + to + ")";
    }
    @Override
    public String toTxt () {
        return super.toString() + " /from " + from + " /to " + to;
    }

}
