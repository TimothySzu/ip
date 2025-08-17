public class Task {

    private boolean marked  =  false;
    private String text;

    public Task (String text) {
        this.text = text;
    }

    public void mark () {
        marked = true;
        System.out.println("________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        System.out.println("________________________________");

    }

    public void unmark () {
        marked = false;
        System.out.println("________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
        System.out.println("________________________________");
    }

    @Override
    public String toString() {
        if (marked) {
            return "[X] " + text;
        } else {
            return "[ ] " + text;
        }
    }
}
