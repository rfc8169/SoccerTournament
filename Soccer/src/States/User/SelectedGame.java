package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectedGame extends States.State {
    final String pathAppend = "";
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public SelectedGame(Role role, Connection connection, SQLstateInfo selectedInfo)
    {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        modifiableData.append(pathAppend);

        try{
            statement = connection.createStatement();
            String sql = "SELECT CONCAT('Game ID:: ', Game_ID),\n" +
                    "CONCAT('Start Time: ', Start_time),\n" +
                    "CONCAT('End Time: ', End_time),\n" +
                    "CONCAT('Field Number: ', field_no),\n" +
                    "CONCAT('Location: ', location),\n" +
                    "CONCAT('Home Team: ', home_team),\n" +
                    "CONCAT('Away Team: ', away_team),\n" +
                    "CONCAT('Tournament:: ', tournament)\n" +
                    "FROM game WHERE game_id = "+selectedInfo.getGame()+";";
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("Game info:");
            while (rs.next()){
                for (int i = 1; i < 9; i++){
                    System.out.println("\t"+rs.getString(i));
                }
            }
            sql = "SELECT home_team, away_team FROM Game WHERE game_id = "+selectedInfo.getGame();
            rs = statement.executeQuery(sql);
            rs.next();
            String hteam = rs.getString(1);
            String ateam = rs.getString(2);
            sql = String.format("SELECT CONCAT( (SELECT home_team FROM game WHERE game_id = %s), ' : ', COUNT(\n" +
                            "(\n" +
                            "SELECT team FROM statistics\n" +
                            "JOIN game on statistics.game_id = game.game_id\n" +
                            "WHERE event = 'Goal' AND game.game_id = %s AND team = home_team\n" +
                            "))) as home,\n" +
                            "CONCAT( (SELECT away_team FROM game WHERE game_id = %s), ' : ', COUNT(\n" +
                            "(\n" +
                            "SELECT team FROM statistics\n" +
                            "JOIN game on statistics.game_id = game.game_id\n" +
                            "WHERE event = 'Goal' AND game.game_id = %s AND team = away_team\n" +
                            "))) as away;",selectedInfo.getGame(),selectedInfo.getGame(),selectedInfo.getGame(),
                    selectedInfo.getGame());
            rs = statement.executeQuery(sql);
            System.out.println("Game Results:");
            rs.next();
            System.out.println("\t"+rs.getString(1));
            System.out.println("\t"+rs.getString(2));
            sql = "SELECT CONCAT(EVENT,' for ',team,' by ',first_name,' ',last_name,' at ',time)" +
                    " FROM statistics JOIN user on statistics.uid = user.uid WHERE game_id = " +
                    selectedInfo.getGame() + "ORDER by time";
            rs = statement.executeQuery(sql);
            System.out.println("Game Statistics");
            while(rs.next()){
                System.out.println("\t"+rs.getString(1));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        while (true) {

            System.out.println(modifiableData);
            help();
            input = scanner.nextLine();

            //determine appropriate return type:
            if (input.equals("/b")) return null;
            else if(input.equals("/e"))return StateType.END;
            else if(input.equals("find player")) return StateType.FINDPLAYER;
            else if(input.equals("find team")) return StateType.FINDTEAM;
            else if(input.equals("enter stats") && super.getRole() == Role.REFEREE) return StateType.ENTERSTATS;
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
    }

    @Override
    public void help() {
        System.out.println("try:\n'find player'\n'find team'\n'enter stats'\n'/e' - to exit\n'/b' - to go back");

    }
}
