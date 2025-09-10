import Exceptions.DukeyException;
import Tasks.DeadLine;
import Tasks.Task;
import Tasks.ToDo;
import Tasks.Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/* Storage class to handle interactions with .txt file */
public class Storage {

    private TaskList taskList;

/**
 * Initialise storage.
 */
 public Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Load .txt file.
     * If no existing file, create new one.
     * If files exists, add tasks to taskList.
     * Handle errors that may arise from opening/ creating file.
     */
    public String load() {
        String output = "";
        //read .txt file
        try {
            // Create a File object pointing to "duke.txt"
            File file = new File("duke.txt");

            // If the file does not exist, create it
            if (!file.exists()) {
                if (file.createNewFile()) {
                    output = "File created: " + file.getName();
                    System.out.println("File created: " + file.getName());
                } else {
                    output = "Failed to create the file.";
                    System.out.println("Failed to create the file.");
                }
            }

            // Read the file
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                add(line); // Add the task to taskList
            }
            scanner.close(); // Always close the scanner after use

        } catch (IOException e) {
            output = "An error occurred while handling the file.";
            System.out.println("An error occurred while handling the file.");
            e.printStackTrace();
        } catch (DukeyException e) {
            output = e.getMessage();
            System.out.println(e.getMessage());
        }
        return output;
    }

    /**
     * Adds .txt representation of task to taskList.
     * Read the .txt format of task and initialise the task.
     * Add the new task to taskList.
     *
     * @throws DukeyException if task description missing,
     * or task type is unspecified,
     */
    public void add (String line) throws DukeyException {
        //format of line in .txt file is : [E][ ] project meeting /from Mon 2pm /to 4pm)
        assert(line.length() > 1);
        char type = line.charAt(1);
        assert(line.length() > 4);
        boolean isMarked =  line.charAt(4) == 'X' ? true : false;
        String rest = line.substring(7);
        Task task = null;
        try {
            switch (type) {
                case 'T':
                    task = new ToDo(rest, isMarked);
                    break;
                case 'D':
                    task = new DeadLine(rest, isMarked);
                    break;
                case 'E':
                    task = new Event(rest, isMarked);
                    break;
                default:
                    throw new DukeyException("Unspecified task type");
            }
        } catch (DukeyException exception) {
            throw exception;
        }
        assert(task != null);
        taskList.addTask(task);
    }

    /**
     * Writes taskList into .txt file.
     */
    public String rewriteFile(String filePath) {
        try {
            // Step 1: Create a FileWriter object in write mode (overwrite mode)
            FileWriter writer = new FileWriter(filePath, false); // false to overwrite
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Step 2: Loop through the ArrayList and write each element to the file
            for (Task task : taskList.arr) {
                assert(task != null);
                bufferedWriter.write(task.toTxt());
                bufferedWriter.newLine(); // Add a new line after each item
            }
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "File has been rewritten with the ArrayList content.";
    }

}
