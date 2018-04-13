package States.Referee;

import States.Role;
import States.StateType;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class CreateTournament extends States.State {
    final String pathAppend = "CreateTournament/";
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public CreateTournament(Role role, Connection connection) {
        super(role, connection);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String name;
        String location;
        String startDate;
        String endDate;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println(modifiableData);
            System.out.println("try 'h' for help, 'c' to create a tournament' or hit enter to go back");
            //determine appropriate return type:
            String input = scanner.nextLine();
            if(input.equals("h")){
                help();
                continue;
            }
            else if(input.equals("e"))return StateType.END;
            else if (!input.equals("c")) return null;

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
            catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
    }

    @Override
    public void help() {
        System.out.println("use 'e' to exit");

    }
}
