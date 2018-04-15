package States.Public;

import States.Role;
import States.SQLstateInfo;
import States.State;
import States.StateType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login extends States.State {
    StringBuilder pathAppend = new StringBuilder("Login/");
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public Login(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String inputName;
        modifiableData.append(pathAppend);
        while (true) {
            System.out.println("Login");
            System.out.print("Proceed with login? (y/n): ");
            String proceed = scanner.nextLine();
            if(proceed.equals("n")) return null;
            System.out.print("Login name: ");
            inputName = scanner.nextLine();

            try{
                statement = connection.createStatement();
                String sql = "SELECT ROLE FROM USER WHERE UID = '"+inputName+"'";
                ResultSet resultSet = statement.executeQuery(sql);
                if(resultSet.next()){
                    System.out.println("Hello "+inputName+", you are in the system!");
                }else{
                    System.out.println("User "+inputName+" could not be found in out system.");
                }
                if(Integer.parseInt(resultSet.getString(1)) == 1) super.setRole(Role.COACH);
                else if(Integer.parseInt(resultSet.getString(1)) == 2) super.setRole(Role.REFEREE);
                else super.setRole(Role.USER);
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

            //determine appropriate return type:
            String appendName = "<"+inputName+">/";
            pathAppend.append(appendName);
            modifiableData.append(appendName);
            selectedInfo.setUser(inputName);
            return StateType.LOGGEDIN;
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
