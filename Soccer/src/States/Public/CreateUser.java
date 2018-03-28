package States.Public;

import States.Role;
import States.StateType;

import java.util.Scanner;

/**
 * Created by User on 3/27/2018.
 */
public class CreateUser extends States.State {
    final String pathAppend = "CreateUser/";
    Scanner scanner = new Scanner(System.in);

    public CreateUser(Role role) {
        super(role);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.print("CreateUser: ");
            input = scanner.nextLine();


            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
            else return StateType.LOGGEDIN;
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
    }

    @Override
    public void help() {

    }
}
