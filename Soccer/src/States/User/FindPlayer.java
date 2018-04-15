package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FindPlayer extends States.State {
    StringBuilder pathAppend = new StringBuilder("FindPlayer/");
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public FindPlayer(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println(modifiableData);
            try{
                statement = connection.createStatement();
                String sql = "SELECT CONCAT(LAST_NAME,', ',First_name,', ',TEAM) FROM USER JOIN PLAYER ORDER BY Last_Name;";
                ResultSet rs = statement.executeQuery(sql);
                System.out.println("PLayers: Lname, Fname, Team");
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("enter '/e' to exit or '/b' to go back");
            System.out.print("enter player (FirstName LastName): ");
            input = scanner.nextLine();

            //determine appropriate return type:
            if (input.equals("/b")) return null;
            else if(input.equals("/e"))return StateType.END;
            else{
                try{
                    statement = connection.createStatement();
                    String sql = "SELECT UID FROM user WHERE First_Name='"+input.split(" ")[0]+
                            "' AND Last_Name='"+input.split(" ")[1]+"';";
                    ResultSet rs = statement.executeQuery(sql);
                    int c = 0;
                    String uid = null;
                    while(rs.next()){
                        uid = rs.getString(1);
                        input = rs.getString(1);
                        c++;
                    }
                    if (c < 1){
                        System.out.println("User not found\n");
                        continue;
                    }
                    statement = connection.createStatement();
                    ResultSet rs2 = statement.executeQuery("SELECT '"+uid+"' IN (SELECT UID FROM PLAYER)");
                    rs2.next();
                    if(rs2.getString(1).equals("FALSE")){
                        System.out.println("--the UID you entered is not registered as a player--");
                        continue;
                    }
                    pathAppend.append("<"+input+">/");
                    modifiableData.append("<"+input+">/");
                    selectedInfo.setPlayer(input);
                    return StateType.SELECTEDPLAYER;
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
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
        pathAppend = new StringBuilder("FindPlayer/");

    }

    @Override
    public void help() {

    }
}
