import java.util.ArrayList;
import java.util.Scanner;

public class Dukey {

    // Store the tasks
    private ArrayList<Task> arr = new ArrayList<>();

    // Entry point of the program
    public static void main(String[] args) {
        System.out.println("________________________________");
        System.out.println("Hello! I'm Dukey");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        Dukey curr = new Dukey();

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

            String[] temp = input.split(" ");  // Split input into commands
            String command = temp[0];
            Task task = null;  // Initialize task object to store created tasks

            // Handle commands
            switch (command) {
                case "list":
                    this.lst();  // Display the task list
                    break;
                case "mark":
                    arr.get(Integer.parseInt(temp[1]) - 1).mark();  // Mark task as done
                    break;
                case "unmark":
                    arr.get(Integer.parseInt(temp[1]) - 1).unmark();  // Mark task as undone
                    break;
                case "delete":
                    delete(Integer.parseInt(temp[1]) - 1);  // Delete a task
                    break;
                case "todo":
                    StringBuilder todoDesc = new StringBuilder();
                    for (int i = 1; i < temp.length; i++) {
                        todoDesc.append(temp[i]).append(" ");  // Build the todo description
                    }
                    if (todoDesc.isEmpty()) {
                        throw new DukeyException("Description Missing!");  // Handle missing description
                    }
                    task = new ToDo(todoDesc.toString().trim());  // Create ToDo task
                    break;
                case "deadline":
                    String deadlineText = "";
                    String deadlineDate = "";
                    StringBuilder deadlineDesc = new StringBuilder();
                    for (int i = 1; i < temp.length; i++) {
                        if (temp[i].equals("/by")) {
                            deadlineText = deadlineDesc.toString();  // Save description before /by
                            deadlineDesc = new StringBuilder();
                        } else {
                            deadlineDesc.append(temp[i]).append(" ");
                        }
                    }
                    if (deadlineText.isEmpty()) {
                        throw new DukeyException("Description Missing!");  // Handle missing description
                    }
                    deadlineDate = deadlineDesc.toString();
                    task = new DeadLine(deadlineText.trim(), deadlineDate.trim());  // Create deadline task
                    break;
                case "event":
                    String eventText = "";
                    String eventFrom = "";
                    String eventTo = "";
                    StringBuilder eventDesc = new StringBuilder();
                    for (int i = 1; i < temp.length; i++) {
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
                    eventTo = eventDesc.toString();
                    task = new Event(eventText.trim(), eventFrom.trim(), eventTo.trim());  // Create event task
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

    // End the program
    public void end() {
        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
}


