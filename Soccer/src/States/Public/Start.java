package States.Public;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.util.Scanner;

public class Start extends States.State {
    final String pathAppend = "Start/";
    Scanner scanner = new Scanner(System.in);

    public Start(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        modifiableData.append(pathAppend);
        while (true) {

            //retrieve input from user:
            System.out.println("Welcome to the Soccer Tournament DB.");
            System.out.print("Enter 'l' for login, 'c' to create a new profile or 'e' to exit the program: ");
            input = scanner.nextLine();

            //determine appropriate return type:
            if (input.equals("l")) return StateType.LOGIN;
            else if (input.equals("c")) return StateType.CREATEUSER;
            else if (input.equals("e")) return StateType.END;
            else System.out.println("Please enter valid input.\n");
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
