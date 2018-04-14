package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.util.Scanner;

public class SelectedTournament extends States.State {
    final String pathAppend = "";
    Scanner scanner = new Scanner(System.in);

    public SelectedTournament(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println(modifiableData);
            System.out.println("try 'h' for help");
            input = scanner.nextLine();

            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
            else if(input.equals("h")) help();
            else if(input.equals("e"))return StateType.END;
            else if (super.getRole() == Role.REFEREE && input.equals("add game")) return StateType.ADDGAME;
            else if (input.equals("find game")) return StateType.FINDGAME;
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
    }

    @Override
    public void help() {
        System.out.println("try:\n'add game'\n'find game'\n'e' - to exit");

    }
}
