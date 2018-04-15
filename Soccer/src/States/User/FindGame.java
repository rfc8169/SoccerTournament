package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class FindGame extends States.State {
    StringBuilder pathAppend = new StringBuilder("FindGame/");
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public FindGame(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String id, input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println(modifiableData);

            try{
                statement = connection.createStatement();
                String sql = "SELECT CONCAT('Game ID: ',GAME_ID,', Location: ', LOCATION,', ', HOME_TEAM,' vs. '," +
                        " AWAY_TEAM, ' Tournament: ', TOURNAMENT) FROM GAME";
                ResultSet rs = statement.executeQuery(sql);
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
                statement.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            System.out.println("\n Select Game ID: ");
            input = scanner.nextLine();


            if (input.equals("")) return null;
            else if(input.equals("h")) help();
            else if(input.equals("e"))return StateType.END;
            else{
                try{
                    statement = connection.createStatement();
                    String sql = "SELECT "+input+" IN (SELECT Game_ID FROM Game)";
                    ResultSet rs = statement.executeQuery(sql);
                    rs.next();
                    if(rs.getString(1).equals("TRUE")){
                        selectedInfo.setGame(input);
                        input = "<"+input+">/";
                        pathAppend.append(input);
                        modifiableData.append(input);
                        return StateType.SELECTEDGAME;
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
        pathAppend = new StringBuilder("FindGame/");

    }

    @Override
    public void help() {
    }
}
