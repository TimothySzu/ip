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
        curr.reply();
    }

    public void reply() {
        Scanner scan = new Scanner(System.in) ;
        String input = scan.nextLine();
        String [] temp = input.split(" ");
        String command = temp [0];

        if (command.equals("bye")) {
            this.end();
            return;

        } else if (command.equals("list")) {
            this.lst();

        } else if (command.equals("mark")) {
            arr[Integer.parseInt(temp[1]) - 1].mark();

        } else if (command.equals("unmark")) {
            arr[Integer.parseInt(temp[1]) - 1].unmark();

        } else {
            Task task;
            if (command.equals("todo")) {
                StringBuilder curr = new StringBuilder();
                for (int i = 1; i < temp.length; i++) {
                    curr.append(temp[i]);
                    curr.append(" ");
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
                dueDate = curr.toString();
                task = new DeadLine(text.trim(), dueDate.trim());

            } else {
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
                to = curr.toString();
                task = new Event(text.trim(), from.trim(), to.trim());
            }

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
