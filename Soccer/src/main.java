import States.Public.*;
import States.Role;
import States.State;
import States.StateType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class main{

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";
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

        //role of user:
        Role currentRole = Role.USER;
        //response from the current stater:
        StateType response = StateType.START;
        //state we are currently in:
        State currentState = stateGenerator.makeState(response, currentRole);

        //database initialization
        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            
        }
        catch(SQLException e){}
        catch (Exception e){}


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
        System.out.println("Exiting...");
    }
}
