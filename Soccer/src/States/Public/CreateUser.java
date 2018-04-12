package States.Public;

import States.Role;
import States.StateType;

import java.sql.Connection;
import java.util.Scanner;

/**
 * Created by User on 3/27/2018.
 */
public class CreateUser extends States.State {
    StringBuilder pathAppend = new StringBuilder("CreateUser/");
    Scanner scanner = new Scanner(System.in);

    public CreateUser(Role role, Connection connection) {
        super(role, connection);
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
            System.out.println("\tGender (Male/Female/Other):");
            String gender = scanner.next();
            System.out.println("\tBirth Date (MM/DD/YYYY): ");
            String birthDate = scanner.next();
            System.out.println("\tHome Town: ");
            String homeTown = scanner.next();
            System.out.println("\tPhone Number (##########): ");
            String phone = scanner.next();
            System.out.println("\tRole (Player (p)/Coach (c)/Referee (r)): ");
            String role = scanner.next();

            //determine appropriate return type:
            input = username;
            input = "<"+input+">/";
            pathAppend.append(input);
            modifiableData.append(input);
            if(role.equals("c")) super.setRole(Role.COACH);
            else if(role.equals("r")) super.setRole(Role.REFEREE);
            else super.setRole(Role.USER);
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
