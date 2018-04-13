package States.Referee;

import States.Role;
import States.StateType;

import java.sql.Connection;
import java.util.Scanner;

public class AddGame extends States.State {

    public AddGame(Role role, Connection connection) {
        super(role, connection);
    }
    final String pathAppend = "AddGame/";
    Scanner scanner = new Scanner(System.in);

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
            System.out.println("try 'h' for help, 'a' to add a game to this tournament' or hit enter to go back");
            //determine appropriate return type:
            String input = scanner.nextLine();
            if(input.equals("h")){
                help();
                continue;
            }
            else if(input.equals("e"))return StateType.END;
            else if (!input.equals("a")) return null;

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
