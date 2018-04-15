package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.util.Scanner;

/**
 * Created by User on 3/27/2018.
 */
public class LoggedIn extends States.State{
    final String pathAppend = "";
    Scanner scanner = new Scanner(System.in);

    public LoggedIn(Role role, Connection connection, SQLstateInfo selectedInfo)
    {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println("--Logged In--");
            System.out.println(modifiableData);
            help();
            input = scanner.nextLine();

            //determine appropriate return type:
            if (input.equals("/b")) return null;
            else if(input.equals("/e"))return StateType.END;
            else if(input.equals("find tournament")) return StateType.FINDTOURNAMENT;
            else if(input.equals("find team")) return StateType.FINDTEAM;
            else if(input.equals("find player")) return StateType.FINDPLAYER;
            else if(input.equals("find game")) return StateType.FINDGAME;
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
        System.out.println("try using:\n'find tournament'\n'find team'\n'find player'\n'find game'");
        System.out.println("'create tournament'\n'create team'\n'/e' - to exit\n '/b' - to go back");

    }
}
