import java.util.Scanner;
public class Dukey {

    Task [] arr;
    int i;

    public static void main(String[] args) {
        System.out.println("________________________________");
        System.out.println("Hello! I'm Dukey");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
        Dukey curr = new Dukey();
        curr.reply();
    }

    public Dukey() {
        arr = new Task [100];
        i = 0;
    }

    public void reply() {
        Scanner scan = new Scanner(System.in) ;
        String input = scan.nextLine();
        if (input.equals("bye")) {
            this.end();
            return;
        }

        else if (input.equals("list")) {
            this.lst();
        }

        else if (input.split(" ")[0].equals("mark")) {
            arr[Integer.parseInt(input.split(" ")[1]) - 1].mark();
        }

        else if (input.split(" ")[0].equals("unmark")) {
            arr[Integer.parseInt(input.split(" ")[1]) - 1].unmark();
        }
        else {
            System.out.println("________________________________");
            System.out.println("added:" + " " + input);
            System.out.println("________________________________");
            arr[i] = new Task(input);
            i++;
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
