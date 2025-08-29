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

public class Storage {

    TaskList tasklist;

    public Storage(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    public void load() {
        //read .txt file
        try {
            // Create a File object pointing to "duke.txt"
            File file = new File("duke.txt");

            // If the file does not exist, create it
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("Failed to create the file.");
                }
            }

            // Read the file
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                add(line); // Add the line to your list (curr)
            }
            scanner.close(); // Always close the scanner after use

        } catch (IOException e) {
            System.out.println("An error occurred while handling the file.");
            e.printStackTrace();
        } catch (DukeyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add (String line) throws DukeyException {
        //format of line in .txt file is : [E][ ] project meeting /from Mon 2pm /to 4pm)
        char type = line.charAt(1);
        boolean isMarked =  line.charAt(4) == 'X' ? true : false;
        String rest = line.substring(7);
        Task task = null;
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
        }
        if(task != null) {
            tasklist.add(task);
        } else {
            throw new DukeyException("Unspecified task type");
        }
    }

    //write task list into the .txt file
    public void rewriteFile(String filePath) {
        try {
            // Step 1: Create a FileWriter object in write mode (overwrite mode)
            FileWriter writer = new FileWriter(filePath, false); // false to overwrite
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Step 2: Loop through the ArrayList and write each element to the file
            for (Task task : tasklist.arr) {
                bufferedWriter.write(task.toTxt());
                bufferedWriter.newLine(); // Add a new line after each item
            }

            // Step 3: Close the writer
            bufferedWriter.close();

            System.out.println("File has been rewritten with the ArrayList content.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
