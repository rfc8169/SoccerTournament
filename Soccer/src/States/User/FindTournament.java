package States.User;

import States.Role;
import States.StateType;

import java.util.Scanner;

public class FindTournament extends States.State {
    final String pathAppend = "FindTournament/";
    Scanner scanner = new Scanner(System.in);

    public FindTournament(Role role) {
        super(role);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println("FindTournament: ");
            input = scanner.nextLine();


            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
            else if(input.equals("h")) help();
            else if(input.equals("e"))return StateType.END;
            else return StateType.SELECTEDTOURNAMENT;
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
