package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FindTournament extends States.State {
    StringBuilder pathAppend = new StringBuilder("FindTournament/");
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public FindTournament(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println(modifiableData);


            try{
                statement = connection.createStatement();
                String sql = "SELECT CONCAT(NAME,', ', LOCATION) FROM TOURNAMENT";
                ResultSet rs = statement.executeQuery(sql);
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

            System.out.println("'/e' to end or '/b' for back");
            System.out.print("Enter the name of a specific tournament for more information: ");
            input = scanner.nextLine();
            //determine appropriate return type:
            if (input.equals("/b")) return null;
            else if(input.equals("/e"))return StateType.END;
            else {
                try{
                    statement = connection.createStatement();
                    String sql = "SELECT '"+input+"' IN (SELECT Name FROM Tournament)";
                    ResultSet rs = statement.executeQuery(sql);
                    rs.next();
                    if(rs.getString(1).equals("TRUE")){
                        selectedInfo.setTournament(input);
                        input = "<"+input+">/";
                        pathAppend.append(input);
                        modifiableData.append(input);
                        return StateType.SELECTEDTOURNAMENT;
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
                    System.out.println("Invalid input, try again");
                    continue;
                }
            }
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
        pathAppend = new StringBuilder("FindTournament/");

    }

    @Override
    public void help() {

    }
}
