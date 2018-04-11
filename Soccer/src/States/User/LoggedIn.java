package States.User;

import States.Role;
import States.StateType;

import java.util.Scanner;

/**
 * Created by User on 3/27/2018.
 */
public class LoggedIn extends States.State{
    final String pathAppend = "";
    Scanner scanner = new Scanner(System.in);

    public LoggedIn(Role role) {
        super(role);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println("\n--Logged In--");
            System.out.println(modifiableData);
            System.out.println("try 'h' for help");
            input = scanner.nextLine();

            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
            else if(input.equals("h")) help();
            else if(input.equals("e"))return StateType.END;
            else if(input.equals("find tournament")) return StateType.FINDTOURNAMENT;
            else if(input.equals("find team")) return StateType.FINDTEAM;
            else if(input.equals("finds player")) return StateType.FINDPLAYER;
            else if(input.equals("create tournament") && super.getRole() == Role.REFEREE)
                return StateType.CREATETOURNAMENT;
            else if(input.equals("create team") && super.getRole() == Role.COACH)
                return StateType.CREATETEAM;

        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
    }

    @Override
    public void help() {
        System.out.println("try using:\n'find tournament'\n'find team'\n'find player'");
        System.out.println("'create tournament'\n'create team'\n'e' - to exit");

    }
}
