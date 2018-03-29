package States.Public;

import States.Role;
import States.StateType;

import java.util.Scanner;

public class Start extends States.State {
    final String pathAppend = "Start/";
    Scanner scanner = new Scanner(System.in);

    public Start(Role role) {
        super(role);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            //retrieve input from user:
            System.out.println("Welcome to the Soccer Tournament DB.");
            System.out.print("\nEnter 'l' for login or 'c' to create a new profile, hit Enter to exit the program: ");
            input = scanner.nextLine();

            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("l")) return StateType.LOGIN;
            else if (input.equals("c")) return StateType.CREATEUSER;
            else if (input.equals("")) return StateType.END;
            else System.out.println("Please enter valid input.\n\n");
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, 5).reverse();
    }

    @Override
    public void help() {

    }
}
