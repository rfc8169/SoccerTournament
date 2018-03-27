import States.Coach.*;
import States.Public.*;
import States.Referee.*;
import States.State;
import States.StateType;
import States.Role;
import States.User.*;

/**
 * Handles the instantiation of all new states
 */
public class StateGenerator {

    public State makeState(StateType type, Role role){
        switch (type) {
            //Coach:
            case ASSIGNPLAYER: return new AssignPlayer(role);
            case CREATEASSIGNPLAYER: return new CreateAssignPlayer(role);
            case CREATETEAM: return new CreateTeam(role);

            //Public:
            case CREATEUSER: return new CreateUser(role);
            case END: return new End(role);
            case LOGIN: return new Login(role);
            case START: return new Start(role);

            //Referee:
            case ADDGAME: return new AddGame(role);
            case ADDTEAM: return new AddTeam(role);
            case ADDTOURNAMENT: return new AddTournament(role);
            case ENTERSTATS: return new EnterStats(role);
            case SETTEAM: return new SetTeam(role);

            //User:
            case FINDGAME: return new FindGame(role);
            case FINDPLAYER: return new FindPlayer(role);
            case FINDTEAM: return new FindTeam(role);
            case FINDTOURNAMENT: return new FindTournament(role);
            case LOGGEDIN: return new LoggedIn(role);
            case SELECTEDGAME: return new SelectedGame(role);
            case SELECTEDPLAYER: return new SelectedPlayer(role);
            case SELECTEDTEAM: return new SelectedTeam(role);
            case SELECTEDTOURNAMENT: return new SelectedTournament(role);

            default: throw new RuntimeException("Bad state passed to generator");
        }
    }
}
