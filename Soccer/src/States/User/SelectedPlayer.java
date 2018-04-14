package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.util.Scanner;

public class SelectedPlayer extends States.State {
    final String pathAppend = "";
    Scanner scanner = new Scanner(System.in);

    public SelectedPlayer(Role role, Connection connection, SQLstateInfo selectedInfo) {
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
            else if(input.equals("add to team") && super.getRole() == Role.COACH) return StateType.ASSIGNPLAYER;
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
    }

    @Override
    public void help() {
        System.out.println("try:\n'add to team'\n'e' - to exit");

    }
}
