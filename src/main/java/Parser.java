import Exceptions.DukeyException;
import Tasks.DeadLine;
import Tasks.Task;
import Tasks.ToDo;
import Tasks.Event;

/* Parser class to decide response to user input. */
public class Parser {

    /**  Dukey chatbot */
    private Dukey dukey;
    /**  list of current tasks */
    private TaskList taskList;
    /**  storage to handle interactions with .txt file */
    private Storage storage;

    /**
     * Initialise parser.
     *
     * @param dukey Dukey chatbot.
     * @param taskList list of current tasks.
     * @param storage to handle interactions with .txt file.
     */
    public Parser(Dukey dukey, TaskList taskList, Storage storage) {
        this.dukey = dukey;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Get user input and process it.
     * Decide the correct response for a given command,
     * and use description if necessary.
     *
     * @param command instruction to be parsed.
     * @param description further description of command.
     * @throws DukeyException if command is unrecognised.
     */
    public void parse(String command, String description) throws DukeyException {

        if (command.equals("bye")) {  // Exit condition
            dukey.end();
            return;
        }
        Task task = null;  // Initialize task object to store created tasks
        // Handle commands
        switch (command) {
            case "list":
                taskList.lst();  // Display the task list
                break;
            case "mark":
                taskList.markTask(description);  // Mark task as done
                break;
            case "unmark":
                taskList.unmarkTask(description);  // Mark task as undone
                break;
            case "delete":
                taskList.deleteTask(Integer.parseInt(description.trim()) - 1);  // Delete a task
                break;
            case "todo":
                task = new ToDo(description.trim(), false);  // Create ToDo task
                break;
            case "deadline":
                task = new DeadLine(description.trim(), false);  // Create deadline task
                break;
            case "event":
                task = new Event(description, false);  // Create event task
                break;
            default:
                throw new DukeyException("Command not found");  // Handle invalid commands
        }

        // If task is not null, add it to the list
        if (task != null) {
            taskList.addTask(task);
        }
        //rewrite the txt file
        storage.rewriteFile("duke.txt");
    }
}

