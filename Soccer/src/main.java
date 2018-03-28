import States.Public.*;
import States.Role;
import States.State;
import States.StateType;

import java.util.ArrayList;

public class main{
    public static void main(String args[]){
        //initialize:
        final int MAX_STATE_DEPTH = 10;
        int depth = 0;
        //holds the current path of states:
        ArrayList<State> statePath = new ArrayList<>(MAX_STATE_DEPTH);
        //data stored here in main, but passed to states for modification:
        StringBuilder modifiableData = new StringBuilder("Path:");
        //used to build new states:
        StateGenerator stateGenerator = new StateGenerator();

        //role of user:
        Role currentRole = Role.USER;
        //response from the current stater:
        StateType response = StateType.START;
        //state we are currently in:
        State currentState = stateGenerator.makeState(response, currentRole);
        //while we have not been returned an end state:
        while (!(currentState instanceof End)){
            //set response (the next states type) after having executed the current state
            response = currentState.exec(modifiableData);
            //role for next state is determined by the current state's role after being executed
            currentRole = currentState.getRole();
            //if no next state was requested, revert to the previous state we were in
            if (response == null){
                currentState.undoDataWrite(modifiableData);
                depth--;
                currentState = statePath.get(depth);
                currentState.undoDataWrite(modifiableData);
                statePath.remove(depth);
            }
            //else, store the current state and load the next state it requested for the next iteration
            else {
                statePath.add(currentState);
                depth++;
                currentState = stateGenerator.makeState(response, currentRole);
            }
        }
        System.out.println(modifiableData);
        System.out.println("Exiting...");
    }
}
