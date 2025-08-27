import java.util.*;
import java.io.*;


public class Dukey {

    // Store the tasks
    private ArrayList<Task> arr = new ArrayList<>();

    // Entry point of the program
    public static void main(String[] args) {
        Dukey curr = new Dukey();

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
                curr.add(line); // Add the line to your list (curr)
            }
            scanner.close(); // Always close the scanner after use

        } catch (IOException e) {
            System.out.println("An error occurred while handling the file.");
            e.printStackTrace();
        } catch (DukeyException e) {
            System.out.println(e.getMessage());
        }





        System.out.println("________________________________");
        System.out.println("Hello! I'm Dukey");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        try {
            curr.reply(); // Start replying to user input
        } catch (DukeyException exception) {
            System.out.println(exception.getMessage()); // Handle any exceptions
        }
    }

    // Main loop to handle user inputs
    public void reply() throws DukeyException {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNextLine()) {
            String input = scan.nextLine().trim();  // Read input and trim excess spaces
            if (input.equals("bye")) {  // Exit condition
                this.end();
                break; // End the loop when "bye" is entered
            }
            //get the command
            String command;
            String rest;
            int firstSpaceIndex = input.indexOf(" ");
            if (firstSpaceIndex > 0) {
                command = input.substring(0, firstSpaceIndex);
                rest = input.substring(firstSpaceIndex + 1);
            } else {
                command = input;
                rest = "";
            }

            Task task = null;  // Initialize task object to store created tasks

            // Handle commands
            switch (command) {
                case "list":
                    this.lst();  // Display the task list
                    break;
                case "mark":
                    arr.get(Integer.parseInt(rest.trim()) - 1).mark();  // Mark task as done
                    break;
                case "unmark":
                    arr.get(Integer.parseInt(rest.trim()) - 1).unmark();  // Mark task as undone
                    break;
                case "delete":
                    delete(Integer.parseInt(rest.trim()) - 1);  // Delete a task
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
                System.out.println("________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                arr.add(task);  // Add task to the list
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                System.out.println("________________________________");
            }
            //rewrite the txt file
            rewriteFile("duke.txt");
        }

        scan.close();  // Close the scanner after use
    }

    // List all tasks
    public void lst() {
        System.out.println("________________________________");
        for (int j = 0; j < arr.size(); j++) {
            System.out.println((j + 1) + "." + arr.get(j).toString());  // Display task with index
        }
        System.out.println("________________________________");
    }

    // Delete a task at the given index
    public void delete(int index) {
        Task temp = arr.get(index);
        arr.remove(index);  // Remove the task
        System.out.println("________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(temp.toString());
        System.out.println("Now you have " + arr.size() + " tasks in the list.");
        System.out.println("________________________________");
    }
    void add (String line) throws DukeyException {
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
            arr.add(task);
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
            for (Task task : arr) {
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



    // End the program
    public void end() {
        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
}


