public class Task {

    protected boolean isMarked  =  false;
    protected String type;
    protected String text;

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
