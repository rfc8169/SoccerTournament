package States.Coach;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateTeam extends States.State {
    final String pathAppend = "CreateTeam/";
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public CreateTeam(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input, teamName, mascot, loc, coach;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println("\n"+modifiableData);
            System.out.println("Create team");
            System.out.println("use '/e' for to end or '/b' to go back or enter to continue");
            System.out.println("Team name: ");
            teamName = scanner.nextLine();

            if (teamName.equals("/b")) return null;
            else if(teamName.equals("/e"))return StateType.END;

            System.out.println("Mascot: ");
            mascot = scanner.nextLine();
            System.out.println("Location: ");
            loc = scanner.nextLine();
            System.out.println("Coach: ");
            coach = scanner.nextLine();

            try {
                statement = connection.createStatement();
                String sql = "INSERT INTO TEAM VALUES(\'"+teamName+"\', \'"+mascot+"\', \'"+loc+"\', \'"+coach+"\')";
                statement.executeUpdate(sql);
                statement.close();
                return StateType.LOGGEDIN;
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
