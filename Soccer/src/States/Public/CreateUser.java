package States.Public;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by User on 3/27/2018.
 */
public class CreateUser extends States.State {
    StringBuilder pathAppend = new StringBuilder("CreateUser/");
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public CreateUser(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println("CreateUser: ");

            //potentially do some work or actions:
            //todo
            System.out.println("Make an Account\n\tUsername: ");
            String username = scanner.next();
            System.out.println("\tFirst Name: ");
            String fName = scanner.next();
            System.out.println("\tLast Name: ");
            String lName = scanner.next();
            System.out.println("\tGender (Male(M)/Female(F)/Other(O)):");
            String gender = scanner.next();
            System.out.println("\tBirth Date (YYYY-MM-DD): ");
            String birthDate = scanner.next();
            System.out.println("\tHome Town: ");
            String homeTown = scanner.next();
            System.out.println("\tRole (Player (0)/Coach (1)/Referee (2)): ");
            String role = scanner.next();

            try {
                statement = connection.createStatement();
                String sql = "INSERT INTO USER VALUES "+
                        "(\'"+username+"\', \'"+fName+"\', \'"+lName+"\', \'"+gender+"\', \'"+birthDate+"\', \'"+homeTown+"\', \'"
                        +role+"\')";
                System.out.print(sql);
                statement.executeUpdate(sql);
                statement.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            //determine appropriate return type:
            input = "<"+username+">/";
            pathAppend.append(input);
            modifiableData.append(input);
            if(role.equals("1")) super.setRole(Role.COACH);
            else if(role.equals("2")) super.setRole(Role.REFEREE);
            else super.setRole(Role.USER);
            selectedInfo.setUser(username);
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
