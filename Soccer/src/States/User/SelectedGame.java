package States.User;

import States.Role;
import States.StateType;

import java.sql.Connection;
import java.util.Scanner;

public class SelectedGame extends States.State {
    final String pathAppend = "";
    Scanner scanner = new Scanner(System.in);

    public SelectedGame(Role role, Connection connection) {
        super(role, connection);
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
            else if(input.equals("find player")) return StateType.FINDPLAYER;
            else if(input.equals("find team")) return StateType.FINDTEAM;
            else if(input.equals("enter stats") && super.getRole() == Role.REFEREE) return StateType.ENTERSTATS;
            else if (input.equals("enter team") && super.getRole() == Role.REFEREE) return  StateType.SETTEAM;
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
    }

    @Override
    public void help() {
        System.out.println("try:\n'find player'\n'find team'\n'enter stats'\n'enter team'\n'e' - to exit");

    }
}
