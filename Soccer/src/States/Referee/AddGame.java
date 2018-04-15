package States.Referee;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class AddGame extends States.State {

    public AddGame(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }
    final String pathAppend = "AddGame/";
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input, id, loc, start, end, fieldNo, home, away, tournament;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println(modifiableData);
            //System.out.println("try 'h' for help");
            System.out.print("add a game to "+selectedInfo.getTournament()+"? (y/n): ");
            input = scanner.nextLine();
            if (input.equals("n")) return null;
            System.out.print("Add game to tournament");
            System.out.println("Game ID: ");
            id = scanner.nextLine();
            System.out.println("Start Time: ");
            start = scanner.nextLine();
            System.out.println("End Time: ");
            end = scanner.nextLine();
            System.out.println("Field Number: ");
            fieldNo = scanner.nextLine();
            System.out.println("Location: ");
            loc = scanner.nextLine();
            System.out.println("Home Team: ");
            home = scanner.nextLine();
            System.out.println("Away Team: ");
            away = scanner.nextLine();
            System.out.println("Tournament Name: ");
            tournament = scanner.nextLine();

            try{
                statement = connection.createStatement();
                String sql = "INSERT INTO GAME VALUES (\'"+id+"\', \'"+start+"\', \'"+end+"\', \'"+fieldNo+"\', \'"+
                        loc+"\', \'"+home+"\', \'"+away+"\', \'"+tournament+"\')";
                statement.executeUpdate(sql);
                statement.close();
            }
            catch(Exception e){

            }
            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
//            if (input.equals("")) return null;
//            else if(input.equals("h")) help();
//            else if(input.equals("e"))return StateType.END;
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
