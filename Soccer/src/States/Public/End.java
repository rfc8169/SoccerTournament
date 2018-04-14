package States.Public;

import States.Role;
import States.SQLstateInfo;
import States.State;
import States.StateType;

import java.sql.Connection;

/**
 * Created by User on 3/27/2018.
 */
public class End extends States.State {

    public End(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
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
