package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String input;
        modifiableData.append(pathAppend);

        try{
            statement = connection.createStatement();
            String sql = "SELECT CONCAT('Game ID: ',GAME_ID,', Location: ', LOCATION,', ', HOME_TEAM,' vs. '," +
                    " AWAY_TEAM, ' Tournament: ', TOURNAMENT) FROM GAME WHERE TOURNAMENT = '"+selectedInfo.getTournament()+"'";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        while (true) {

            System.out.println("\n"+modifiableData);

            help();
            System.out.print("Select Game ID: ");
            input = scanner.nextLine();

            if (input.equals("/b")) return null;
            else if(input.equals("/e"))return StateType.END;
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
                catch (SQLException e){
                    int errorInt = e.getErrorCode();
                    if(errorInt == 90039 || errorInt == 90067 || errorInt == 90098) {
                        System.out.println("Your connection to our database has been close, please restart the program.");
                        return StateType.END;
                    }else{
                        System.out.println("Invalid input, try again");
                        continue;
                    }
                }
                catch(Exception e){
                    System.out.println("Invalid input, try again");
                    continue;
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
        System.out.println("use '/e' to end or '/b' to go back");
    }
}
