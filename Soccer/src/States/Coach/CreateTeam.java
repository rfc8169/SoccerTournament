package States.Coach;

import States.Role;
import States.StateType;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class CreateTeam extends States.State {
    final String pathAppend = "CreateTeam/";
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public CreateTeam(Role role, Connection connection) {
        super(role, connection);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input, teamName, mascot, loc, coach;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println(modifiableData);
            System.out.println("try 'h' for help");
            System.out.println("Create team");
            System.out.println("Team name: ");
            teamName = scanner.nextLine();

            if (teamName.equals("")) return null;
            else if(teamName.equals("h")) help();
            else if(teamName.equals("e"))return StateType.END;

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
            catch(Exception e){
                e.printStackTrace();
            }
            //potentially do some work or actions:
            //todo

            //determine appropriate return type:

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
