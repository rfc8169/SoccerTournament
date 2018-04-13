import States.Coach.*;
import States.Public.*;
import States.Referee.*;
import States.SQLstateInfo;
import States.State;
import States.StateType;
import States.Role;
import States.User.*;

import java.sql.Connection;

/**
 * Handles the instantiation of all new states
 */
public class StateGenerator {

    public State makeState(StateType type, Role role, Connection connection, SQLstateInfo info){
        switch (type) {
            //Coach:
            case ASSIGNPLAYER: return new AssignPlayer(role, connection);
            case CREATEASSIGNPLAYER: return new CreateAssignPlayer(role, connection);
            case CREATETEAM: return new CreateTeam(role, connection);

            //Public:
            case CREATEUSER: return new CreateUser(role, connection);
            case END: return new End(role, connection);
            case LOGIN: return new Login(role, connection);
            case START: return new Start(role, connection);

            //Referee:
            case ADDGAME: return new AddGame(role, connection);
            case CREATETOURNAMENT: return new CreateTournament(role, connection);
            case ENTERSTATS: return new EnterStats(role, connection);
            case SETTEAM: return new SetTeam(role, connection);

            //User:
            case FINDGAME: return new FindGame(role, connection);
            case FINDPLAYER: return new FindPlayer(role, connection);
            case FINDTEAM: return new FindTeam(role, connection);
            case FINDTOURNAMENT: return new FindTournament(role, connection);
            case LOGGEDIN: return new LoggedIn(role, connection);
            case SELECTEDGAME: return new SelectedGame(role, connection);
            case SELECTEDPLAYER: return new SelectedPlayer(role, connection);
            case SELECTEDTEAM: return new SelectedTeam(role, connection);
            case SELECTEDTOURNAMENT: return new SelectedTournament(role, connection);

            default: throw new RuntimeException("Bad state passed to generator");
        }
    }
}
