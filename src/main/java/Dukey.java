import java.util.Scanner;
public class Dukey {

    String [] arr;
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
        arr = new String [100];
        i = 0;
    }

    public void reply() {
        Scanner scan = new Scanner(System.in) ;
        String input = scan.nextLine();
        if (input.equals("bye")) {
            this.end();
        }
        else if (input.equals("list")) {
            this.lst();
            this.reply();
        }
        else {
            System.out.println("________________________________");
            System.out.println("added:" + " " + input);
            System.out.println("________________________________");
            arr[i] = input;
            i++;
            this.reply();
        }
    }

    public void lst() {
        System.out.println("________________________________");
        for (int j = 0; j < i; j++) {
            System.out.println(j + 1 + "." + " " + arr[j]);
        }
        System.out.println("________________________________");
    }
    public void end() {
        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
}
