import java.util.ArrayList;
import java.util.Scanner;

public class Dukey {

    private Storage storage;
    private TaskList tasklist;
    private Parser parser;
    private Ui ui;

    public Dukey() {

        this.tasklist = new TaskList();
        this.storage = new Storage(tasklist);
        this.parser = new Parser(this, tasklist, storage);
        this.ui = new Ui(parser);

        System.out.println("________________________________");
        System.out.println("Hello! I'm Dukey");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        try {
            ui.reply(); // Start replying to user input
        } catch (DukeyException exception) {
            System.out.println(exception.getMessage()); // Handle any exceptions
        }
    }

    // End the program
    public void end() {
        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
}
