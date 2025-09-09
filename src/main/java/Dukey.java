import Exceptions.DukeyException;

import java.util.ArrayList;
import java.util.Scanner;

/** Dukey class represents chatbot */
public class Dukey {

    /* storage object to handle interactions with .txt file */
    private Storage storage;
    /* list of tasks */
    private TaskList taskList;
    /* parser to decide how to respond to user input */
    private Parser parser;
    /* ui to receive and process user input */
    private Ui ui;
    /* reply message to output */
    private String output;
    /**
     * Initialises Dukey chatbot with the required supporting classes.
     */
    public Dukey() {

        //Initialise supporting classes
        this.taskList = new TaskList();
        this.storage = new Storage(taskList);
        this.parser = new Parser(this, taskList, storage);
        this.ui = new Ui(parser);

        System.out.println("________________________________");
        System.out.println("Hello! I'm Dukey");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
    }

    /**
     * Ends program.
     */
    public void end() {
        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
    /**
     * Initialises Dukey chatbot with the required supporting classes.
     * @return String return message
     */
    String reply(String input) {
        try {
            return ui.reply(input); // Start taking and replying to user input
        } catch (DukeyException exception) {
            System.out.println(exception.getMessage());
            return exception.getMessage(); // Handle any exceptions
        }
    }

}
