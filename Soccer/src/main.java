import States.Public.*;
import States.Role;
import States.SQLstateInfo;
import States.State;
import States.StateType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class main{

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/SoccerTournament/soccer_tournament";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String args[]){
        //initialize:
        Connection connection = null;
        Statement statement = null;
        final int MAX_STATE_DEPTH = 10;
        int depth = 0;
        //holds the current path of states:
        ArrayList<State> statePath = new ArrayList<>(MAX_STATE_DEPTH);
        //data stored here in main, but passed to states for modification:
        StringBuilder modifiableData = new StringBuilder("Path:");
        //used to build new states:
        StateGenerator stateGenerator = new StateGenerator();
        //holds selected state info:
        SQLstateInfo selected = new SQLstateInfo();

        //role of user:
        Role currentRole = Role.USER;
        //response from the current stater:
        StateType response = StateType.START;
        //state we are currently in:
        State currentState;

        //database initialization
        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            currentState = stateGenerator.makeState(response, currentRole, connection, selected);

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
                    selected = currentState.getSelectedInfo();
                    depth++;
                    currentState = stateGenerator.makeState(response, currentRole, connection, selected.deepCopy());
                }
            }
            connection.close();
        }
        catch(SQLException e){e.printStackTrace();}
        catch (Exception e){e.printStackTrace();}

        //while we have not been returned an end state:
        System.out.println("\nExiting...");
    }
}
