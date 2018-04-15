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
            case ASSIGNPLAYER: return new AssignPlayer(role, connection, info);
            case CREATETEAM: return new CreateTeam(role, connection, info);

            //Public:
            case CREATEUSER: return new CreateUser(role, connection, info);
            case END: return new End(role, connection, info);
            case LOGIN: return new Login(role, connection, info);
            case START: return new Start(role, connection, info);

            //Referee:
            case ADDGAME: return new AddGame(role, connection, info);
            case CREATETOURNAMENT: return new CreateTournament(role, connection, info);
            case ENTERSTATS: return new EnterStats(role, connection, info);

            //User:
            case FINDGAME: return new FindGame(role, connection, info);
            case FINDPLAYER: return new FindPlayer(role, connection, info);
            case FINDTEAM: return new FindTeam(role, connection, info);
            case FINDTOURNAMENT: return new FindTournament(role, connection, info);
            case LOGGEDIN: return new LoggedIn(role, connection, info);
            case SELECTEDGAME: return new SelectedGame(role, connection, info);
            case SELECTEDPLAYER: return new SelectedPlayer(role, connection, info);
            case SELECTEDTEAM: return new SelectedTeam(role, connection, info);
            case SELECTEDTOURNAMENT: return new SelectedTournament(role, connection, info);

            default: throw new RuntimeException("Bad state passed to generator");
        }
    }
}
