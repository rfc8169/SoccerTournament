package States.Public;

import States.Role;
import States.State;
import States.StateType;

/**
 * Created by User on 3/27/2018.
 */
public class End extends States.State {

    public End(Role role) {
        super(role);
    }
    final String pathAppend = "End/";

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            return null;
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
    }

    @Override
    public void help() {

    }
}
