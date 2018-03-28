package States.User;

import States.Role;
import States.StateType;

public class SelectedTournament extends States.State {
    final String pathAppend = "SelectedTournament/";

    public SelectedTournament(Role role) {
        super(role);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        while (true) {
            //temporarily using to track state path as example
            modifiableData.append(pathAppend);

            System.out.println("SelectedTournament: a - add game, f find game");
            System.out.println("\n: ");
            input = System.console().readLine();

            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
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

    }
}
