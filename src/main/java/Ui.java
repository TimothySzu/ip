import Exceptions.DukeyException;

import java.util.Scanner;

class Ui {

    Parser parser;

    public Ui(Parser parser) {
        this.parser = parser;
    }

    public void reply() throws DukeyException {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNextLine()) {

            String input = scan.nextLine().trim();  // Read input and trim excess spaces
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
            parser.parse(command, rest);
        }
        scan.close();  // Close the scanner after use
    }
}


