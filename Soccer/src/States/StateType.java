package States;

/**
 * Created by User on 3/27/2018.
 */
public enum StateType {
    //Coach:
    ASSIGNPLAYER,
    CREATEASSIGNPLAYER,
    CREATETEAM,

    //Public:
    CREATEUSER,
    END,
    LOGIN,
    ROLE,
    START,

    //Referee:
    ADDGAME,
    ADDTEAM,
    ADDTOURNAMENT,
    ENTERSTATS,
    SETTEAM,

    //User:
    FINDGAME,
    FINDPLAYER,
    FINDTEAM,
    FINDTOURNAMENT,
    LOGGEDIN,
    SELECTEDGAME,
    SELECTEDPLAYER,
    SELECTEDTEAM,
    SELECTEDTOURNAMENT
}
