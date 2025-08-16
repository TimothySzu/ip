import java.util.Scanner;
public class Dukey {
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
        if (input.equals("bye")) {
            this.end();
        }
        else {
            System.out.println("________________________________");
            System.out.println(input);
            System.out.println("________________________________");
            this.reply();
        }
    }

    public void end() {
        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
}
