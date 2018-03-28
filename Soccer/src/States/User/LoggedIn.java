package States.User;

import States.Role;
import States.StateType;

/**
 * Created by User on 3/27/2018.
 */
public class LoggedIn extends States.State{
    final String pathAppend = "LoggedIn/";

    public LoggedIn(Role role) {
        super(role);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        while (true) {
            //temporarily using to track state path as example
            modifiableData.append(pathAppend);

            System.out.println("CreateUser: ");
            System.out.println("\nfto- find tournament\nfte - find team\nfp - find player");
            System.out.println("\ncto- find tournament\ncte - find team\n: ");
            input = System.console().readLine();

            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
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

    }
}
