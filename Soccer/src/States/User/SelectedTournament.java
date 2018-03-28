package States.User;

import States.Role;
import States.StateType;

import java.util.Scanner;

public class SelectedTournament extends States.State {
    final String pathAppend = "SelectedTournament/";
    Scanner scanner = new Scanner(System.in);

    public SelectedTournament(Role role) {
        super(role);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println("SelectedTournament: a - add game, f find game");
            System.out.print("\n: ");
            input = scanner.nextLine();

            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
            else if(input.equals("h")) help();
            else if(input.equals("e"))return StateType.END;
            else if (super.getRole() == Role.REFEREE && input.equals("a")) return StateType.ADDGAME;
            else if (input.equals("f")) return StateType.FINDGAME;
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
