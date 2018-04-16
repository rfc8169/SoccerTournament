package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FindTeam extends States.State {
    StringBuilder pathAppend = new StringBuilder("FindTeam/");
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public FindTeam(Role role, Connection connection, SQLstateInfo selectedInfo)
    {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        modifiableData.append(pathAppend);

        if(selectedInfo.getGame() != null){
            try{
                String home;
                String away;
                statement = connection.createStatement();
                String sql = "SELECT HOME_TEAM FROM GAME WHERE GAME_ID ='" + selectedInfo.getGame() + "'";
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                away = rs.getString(1);
                sql = "SELECT AWAY_TEAM FROM GAME WHERE GAME_ID ='" + selectedInfo.getGame() + "'";
                rs = statement.executeQuery(sql);
                rs.next();
                home = rs.getString(1);
                System.out.println("Teams:");
                System.out.println(home);
                System.out.println(away);
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            try {
                statement = connection.createStatement();
                String sql = "SELECT NAME FROM TEAM";
                ResultSet rs = statement.executeQuery(sql);
                System.out.println("Teams:");
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (true) {

            System.out.println("\n"+modifiableData);
            System.out.println("use '/e' to exit or '/b' to go back");
            System.out.print("enter team: ");
            input = scanner.nextLine();

            //determine appropriate return type:
            if (input.equals("/b")) return null;
            else if(input.equals("/e"))return StateType.END;
            else {
                try{
                    statement = connection.createStatement();
                    String sql = "SELECT '"+input+"' IN (SELECT Name FROM Team)";
                    ResultSet rs = statement.executeQuery(sql);
                    rs.next();
                    if(rs.getString(1).equals("TRUE")){
                        selectedInfo.setTeam(input);
                        input = "<"+input+">/";
                        pathAppend.append(input);
                        modifiableData.append(input);
                        return StateType.SELECTEDTEAM;
                    }
                }catch (SQLException e){
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
                    continue;
                }
            }
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
        pathAppend = new StringBuilder("FindTeam/");

    }

    @Override
    public void help() {

    }
}
