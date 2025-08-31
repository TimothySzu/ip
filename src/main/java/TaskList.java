import Tasks.Task;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> arr;

    public TaskList () {
        this.arr = new ArrayList<Task>();
    }

    public int size() {
        return arr.size();
    }

    public void add(Task task) {
        System.out.println("________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        arr.add(task);
        System.out.println("Now you have " + size() + " tasks in the list.");
        System.out.println("________________________________");
    }

    public void mark (String text) {
        arr.get(Integer.parseInt(text.trim()) - 1).mark();
    }

    public void unmark (String text) {
        arr.get(Integer.parseInt(text.trim()) - 1).unmark();
    }

    // List all tasks
    public void lst() {
        System.out.println("________________________________");
        for (int j = 0; j < arr.size(); j++) {
            System.out.println((j + 1) + "." + arr.get(j).toString());  // Display task with index
        }
        System.out.println("________________________________");
    }
    /**
     * Filters list by description and prints it
     *
     * @param desc Description of what we are filtering for.
     */
    public void findTask(String desc) {
        int index = 1;
        System.out.println("________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : arr) {
            if (task.getDesc().contains(desc)) {
                System.out.println(index + "." + task.toString());
                index ++;
            }
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

}
