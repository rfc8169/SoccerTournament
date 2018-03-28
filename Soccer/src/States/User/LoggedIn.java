package States.User;

import States.Role;
import States.StateType;

import java.util.Scanner;

/**
 * Created by User on 3/27/2018.
 */
public class LoggedIn extends States.State{
    final String pathAppend = "LoggedIn/";
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

            System.out.println("LoggedIn: ");
            System.out.println("\nfto- find tournament\nfte - find team\nfp - find player");
            System.out.print("cto- create tournament\ncte - create team\nh - for help\n: ");
            input = scanner.nextLine();

            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
            else if(input.equals("h")) help();
            else if(input.equals("e"))return StateType.END;
            else if(input.equals("fto")) return StateType.FINDTOURNAMENT;
            else if(input.equals("fte")) return StateType.FINDTEAM;
            else if(input.equals("fp")) return StateType.FINDPLAYER;
            else if(input.equals("cto")) return StateType.CREATETOURNAMENT;
            else if(input.equals("cte")) return StateType.CREATETEAM;

        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
    }

    @Override
    public void help() {
        System.out.println("use 'e' to exit");

    }
}
