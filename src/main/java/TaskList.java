import Tasks.Task;

import java.util.ArrayList;

/* TaskList implements a list of current tasks */
public class TaskList {

    /* list of tasks */
    protected ArrayList<Task> arr;

    /**
     * Initialise TaskList.
     */
    public TaskList () {
        this.arr = new ArrayList<Task>();
    }

    public int size() {
        return arr.size();
    }

    /**
     * Adds task to list.
     *
     * @param task task to be added to list.
     */
    public String addTask(Task task) {
        System.out.println("________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        arr.add(task);
        System.out.println("Now you have " + size() + " tasks in the list.");
        System.out.println("________________________________");
        return "Got it. I've added this task:\n" +  task.toString() + "\n"
                + "Now you have " + size() + " tasks in the list.";
    }

    public String markTask (String text) {
        return arr.get(Integer.parseInt(text.trim()) - 1).mark();
    }

    public String unmarkTask (String text) {
        return arr.get(Integer.parseInt(text.trim()) - 1).unmark();
    }

    /**
     * Prints out all tasks on current list.
     */
    public String lst() {
        String output = "";
        System.out.println("________________________________");
        for (int j = 0; j < arr.size(); j++) {
            System.out.println((j + 1) + "." + arr.get(j).toString());  // Display task with index
            output += (j + 1) + "." + arr.get(j).toString() + "\n";
        }
        System.out.println("________________________________");
        return output.stripTrailing();
    }
    /**
     * Filters list by description and prints it
     *
     * @param desc Description of what we are filtering for.
     */
    public String findTask(String desc) {
        String output = "";
        int index = 1;
        System.out.println("________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : arr) {
            if (task.getDesc().contains(desc)) {
                System.out.println(index + "." + task.toString());
                output += index + "." + task.toString() + "\n";
                index ++;
            }
        }
        System.out.println("________________________________");
        return output.stripTrailing();
    }

    /**
     * Removes task at index.
     *
     * @param index index of task to be removed.
     */
    public String deleteTask(int index) {
        Task temp = arr.get(index);
        arr.remove(index);  // Remove the task
        System.out.println("________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(temp.toString());
        System.out.println("Now you have " + arr.size() + " tasks in the list.");
        System.out.println("________________________________");
        return "Noted. I've removed this task:\n" + temp.toString()
                + "\n" + "Now you have " + arr.size() + " tasks in the list.";
    }

}
