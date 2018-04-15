package States;

/**
 * Created by User on 3/27/2018.
 */
public enum StateType {
    //Coach:
    ASSIGNPLAYER,
    CREATETEAM,

    //Public:
    CREATEUSER,
    END,
    LOGIN,
    START,

    //Referee:
    ADDGAME,
    CREATETOURNAMENT,
    ENTERSTATS,

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
