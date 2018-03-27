package States.Coach;

import States.Public.Login;
import States.Role;
import States.StateType;

import java.io.Console;

public class AssignPlayer extends States.State {
    final String pathAppend = "AssignPlayer/";

    public AssignPlayer(Role role) {
        super(role);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        while (true) {
            //temporarily using to track state path as example
            modifiableData.append(pathAppend);

            System.out.println("CreateUser: ");
            input = System.console().readLine();


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
