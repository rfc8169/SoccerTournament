package States.Referee;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EnterStats extends States.State {
    final String pathAppend = "EnterStats/";
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public EnterStats(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println("\n"+modifiableData);
            System.out.println("Enter Statistics");
            System.out.println("Enter a statistic? (y/n): ");
            String ans = scanner.nextLine();
            if(ans.equals("n")) return null;
            System.out.println("Game ID: "+selectedInfo.getGame());
            System.out.print("Is this the correct game id? (y/n): ");
            String response = scanner.nextLine();
            if (response.equals("n")) return null;
            System.out.print("Time (HH.MM): ");
            String time = scanner.nextLine();
            System.out.print("UID: ");
            String uid = scanner.nextLine();
            ResultSet rs;
            String team = null;
            try {
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT TEAM FROM PLAYER WHERE UID = '" + uid+"'");
                rs.next();
                System.out.println("Team: "+ rs.getString(1));
                System.out.print("Is this team correct? (y/n): ");
                String ans2 = scanner.nextLine();
                if(ans2.equals("n")) return null;
                team = rs.getString(1);
            }catch (Exception e){}
            System.out.print("Event (Goal, Assist, Save, Penalty, Yellow Card, Red Card): ");
            String event = scanner.nextLine();
            try{
                statement = connection.createStatement();
                ResultSet rs2 = statement.executeQuery("SELECT '"+uid+"' IN (SELECT UID FROM PLAYER)");
                rs2.next();
                if(rs2.getString(1).equals("FALSE")){
                    System.out.println("--the UID you entered is not registered as a player--");
                    continue;
                }
                String sql = "INSERT INTO STATISTICS VALUES (\'"+selectedInfo.getGame()+"\', "+time+", \'"+uid+"\', \'"+team+"\', \'"+
                        event+"\')";
                statement.executeUpdate(sql);
                statement.close();
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
                continue;
            }
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
    }

    @Override
    public void help() {

    }
}
