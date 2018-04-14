package States.Coach;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.util.Scanner;

public class AssignPlayer extends States.State {
    String pathAppend = "AssignPlayer/";
    Scanner scanner = new Scanner(System.in);

    public AssignPlayer(Role role, Connection connection, SQLstateInfo selectedInfo
    ) {
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
            System.out.print("Assign player to team: ");
            input = scanner.nextLine();


            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
            else if(input.equals("h")) help();
            else if(input.equals("e"))return StateType.END;
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
