import java.util.Scanner;
public class Dukey {

    Task [] arr = new Task[100];
    int i = 0;

    public static void main(String[] args) {
        System.out.println("________________________________");
        System.out.println("Hello! I'm Dukey");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
        Dukey curr = new Dukey();
        try {
            curr.reply();
        } catch (DukeyException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void reply() throws DukeyException {
        Scanner scan = new Scanner(System.in) ;
        String input = scan.nextLine();
        String [] temp = input.split(" ");
        String command = temp [0];
        Task task = null;

        if (command.equals("bye")) {
            this.end();
            return;

        } else if (command.equals("list")) {
            this.lst();

        } else if (command.equals("mark")) {
            arr[Integer.parseInt(temp[1]) - 1].mark();

        } else if (command.equals("unmark")) {
            arr[Integer.parseInt(temp[1]) - 1].unmark();

        } else if (command.equals("todo")) {
                StringBuilder curr = new StringBuilder();
                for (int i = 1; i < temp.length; i++) {
                    curr.append(temp[i]);
                    curr.append(" ");
                }
                if (curr.isEmpty()) {
                    throw new DukeyException("Description Missing!");
                }
                task = new ToDo(curr.toString().trim());

        } else if (command.equals("deadline")) {
                String dueDate;
                String text = "";
                StringBuilder curr = new StringBuilder();
                for (int i = 1; i < temp.length; i++) {
                    if (temp[i].equals("/by")) {
                        text = curr.toString();
                        curr = new StringBuilder();
                    } else {
                        curr.append(temp[i]);
                        curr.append(" ");
                    }
                }
                if (text.isEmpty()) {
                    throw new DukeyException("Description Missing!");
                }
                dueDate = curr.toString();
                task = new DeadLine(text.trim(), dueDate.trim());

        } else if (command.equals("event")) {
                String from = "";
                String to;
                String text = "";
                StringBuilder curr = new StringBuilder();
                for (int i = 1; i < temp.length; i++) {
                    if (temp[i].equals("/from")) {
                        text = curr.toString();
                        curr = new StringBuilder();
                    } else if (temp[i].equals("/to")) {
                        from = curr.toString();
                        curr = new StringBuilder();
                    }
                    else {
                        curr.append(temp[i]);
                        curr.append(" ");
                    }
                }
                if (text.isEmpty()) {
                    throw new DukeyException("Description Missing!");
                }
                to = curr.toString();
                task = new Event(text.trim(), from.trim(), to.trim());

        } else {
            throw new DukeyException("Command not found");
        }
        if (task != null) {
            System.out.println("________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            arr[i] = task;
            i++;
            System.out.println("Now you have " + i + " tasks in the list.");
            System.out.println("________________________________");
        }
        this.reply();
    }

    public void lst() {
        System.out.println("________________________________");
        for (int j = 0; j < i; j++) {
            System.out.println(j + 1 + "." + arr[j].toString());
        }
        System.out.println("________________________________");
    }

    public void end() {
        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
}
