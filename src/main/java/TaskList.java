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
    public void addTask(Task task) {
        System.out.println("________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        arr.add(task);
        System.out.println("Now you have " + size() + " tasks in the list.");
        System.out.println("________________________________");
    }

    public void markTask (String text) {
        arr.get(Integer.parseInt(text.trim()) - 1).mark();
    }

    public void unmarkTask (String text) {
        arr.get(Integer.parseInt(text.trim()) - 1).unmark();
    }

    /**
     * Prints out all tasks on current list.
     */
    public void lst() {
        System.out.println("________________________________");
        for (int j = 0; j < arr.size(); j++) {
            System.out.println((j + 1) + "." + arr.get(j).toString());  // Display task with index
        }
        System.out.println("________________________________");
    }

    /**
     * Removes task at index.
     *
     * @param index index of task to be removed.
     */
    public void deleteTask(int index) {
        Task temp = arr.get(index);
        arr.remove(index);  // Remove the task
        System.out.println("________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(temp.toString());
        System.out.println("Now you have " + arr.size() + " tasks in the list.");
        System.out.println("________________________________");
    }

}
