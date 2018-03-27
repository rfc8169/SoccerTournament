package States.Public;

import States.Role;
import States.StateType;

public class Start extends States.State {
    final String pathAppend = "Start/";

    public Start(Role role) {
        super(role);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        while (true) {
            //temporarily using to track state path as example
            modifiableData.append(pathAppend);

            //retrieve input from user:
            System.out.println("Welcome to the Soccer Tournament DB.");
            System.out.println("\nEnter 'l' for login or 'c' to create a new profile Enter to exit the program: ");
            input = System.console().readLine();

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
