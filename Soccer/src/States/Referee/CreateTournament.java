package States.Referee;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateTournament extends States.State {
    final String pathAppend = "CreateTournament/";
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public CreateTournament(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String name;
        String location;
        String startDate;
        String endDate;
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println("\n"+modifiableData);
            System.out.println("try '/e' to end, '/b' to go back or enter to continue");
            //determine appropriate return type:
            String input = scanner.nextLine();
            if(input.equals("/e"))return StateType.END;
            else if (input.equals("/b")) return null;

            System.out.print("Enter tournament name: ");
            name = scanner.nextLine();
            System.out.print("Enter tournament location: ");
            location = scanner.nextLine();
            System.out.print("Enter tournament start date (YYYY-MM-DD): ");
            startDate = scanner.nextLine();
            System.out.print("Enter tournament end date (YYYY-MM-DD): ");
            endDate = scanner.nextLine();

            String sql = "INSERT INTO TOURNAMENT VALUES"+
                    "('"+name+"','"+location+"','"+startDate+"','"+endDate+"')";
            System.out.println(sql);
            try {
                statement = connection.createStatement();
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
