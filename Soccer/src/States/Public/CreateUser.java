package States.Public;

import States.User.LoggedIn;

/**
 * Created by User on 3/27/2018.
 */
public class CreateUser extends States.State {
    @Override
    public void exec() {
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
        int phone = scanner.nextInt();
        System.out.println("\tRole (Player (P)/Coach (C)/Referee (R)): ");
        String role = scanner.next();


        LoggedIn loggedIn = new LoggedIn();
        loggedIn.exec();
    }

    @Override
    public void help() {

    }
}
