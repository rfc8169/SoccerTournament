package States.User;

import States.Role;
import States.StateType;

public class FindTournament extends States.State {
    final String pathAppend = "FindTournament/";

    public FindTournament(Role role) {
        super(role);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        while (true) {
            //temporarily using to track state path as example
            modifiableData.append(pathAppend);

            System.out.println("FindTournament: ");
            input = System.console().readLine();


            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
            else return StateType.SELECTEDTOURNAMENT;
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
