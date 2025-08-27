import Exceptions.DukeyException;
import Tasks.DeadLine;
import Tasks.Task;
import Tasks.ToDo;
import Tasks.Event;

public class Parser {

    private Dukey dukey;
    private TaskList tasklist;
    private Storage storage;

    public Parser(Dukey dukey, TaskList taskList, Storage storage) {
        this.dukey = dukey;
        this.tasklist = taskList;
        this.storage = storage;
    }

    public void parse(String command, String rest) throws DukeyException {

        if (command.equals("bye")) {  // Exit condition
            dukey.end();
            return;
        }
        Task task = null;  // Initialize task object to store created tasks
        // Handle commands
        switch (command) {
            case "list":
                tasklist.lst();  // Display the task list
                break;
            case "mark":
                tasklist.mark(rest);  // Mark task as done
                break;
            case "unmark":
                tasklist.unmark(rest);  // Mark task as undone
                break;
            case "delete":
                tasklist.delete(Integer.parseInt(rest.trim()) - 1);  // Delete a task
                break;
            case "todo":
                task = new ToDo(rest.trim(), false);  // Create ToDo task
                break;
            case "deadline":
                task = new DeadLine(rest.trim(), false);  // Create deadline task
                break;
            case "event":
                task = new Event(rest, false);  // Create event task
                break;
            default:
                throw new DukeyException("Command not found");  // Handle invalid commands
        }

        // If task is not null, add it to the list
        if (task != null) {
            tasklist.add(task);
        }
        //rewrite the txt file
        storage.rewriteFile("duke.txt");
    }
}

