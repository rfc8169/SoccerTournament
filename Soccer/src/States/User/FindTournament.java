package States.User;

import States.Role;
import States.StateType;

import java.sql.Connection;
import java.util.Scanner;

public class FindTournament extends States.State {
    StringBuilder pathAppend = new StringBuilder("FindTournament/");
    Scanner scanner = new Scanner(System.in);

    public FindTournament(Role role, Connection connection) {
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
            System.out.print("enter tournament: ");
            input = scanner.nextLine();

            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
            else if(input.equals("h")) help();
            else if(input.equals("e"))return StateType.END;
            else{
                input = "<"+input+">/";
                pathAppend.append(input);
                modifiableData.append(input);
                return StateType.SELECTEDTOURNAMENT;
            }
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
        pathAppend = new StringBuilder("FindTournament/");

    }

    @Override
    public void help() {
        System.out.println("use 'e' to exit");

    }
}
