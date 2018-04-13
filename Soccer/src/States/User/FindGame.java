package States.User;

import States.Role;
import States.StateType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class FindGame extends States.State {
    StringBuilder pathAppend = new StringBuilder("FindGame/");
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public FindGame(Role role, Connection connection) {
        super(role, connection);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input, secondTeam;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println(modifiableData);
            System.out.println("try 'h' for help");
            System.out.println("Search for a game");
            System.out.println("Enter the first team: ");
            input = scanner.nextLine();
            System.out.println("Enter the second team: ");
            secondTeam = scanner.nextLine();


            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
//            if (input.equals("")) return null;
//            else if(input.equals("h")) help();
//            else if(input.equals("e"))return StateType.END;
//            else{
//                input = "<"+input+">/";
//                pathAppend.append(input);
//                modifiableData.append(input);
//                return StateType.SELECTEDGAME;
//            }

            try{
                statement = connection.createStatement();
                String sql = "SELECT * FROM GAME WHERE \'"+input+"\' = HOME_TEAM "+
                        "AND \'"+secondTeam+"\' = AWAY_TEAM";
                System.out.println(sql);
                ResultSet rs = statement.executeQuery(sql);
                while(rs.next()){
                    System.out.println("Found stuff: "+rs.getString(1)+", "+rs.getString(2)+", "
                    +rs.getString(3)+", "+rs.getString(4)+", "+rs.getString(5)+", "+
                    rs.getString(6)+", "+rs.getString(7)+", "+rs.getString(8));
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

            if (input.equals("")) return null;
            else if(input.equals("h")) help();
            else if(input.equals("e"))return StateType.END;
            else{
                input = "<"+input+">/";
                pathAppend.append(input);
                modifiableData.append(input);
                return StateType.SELECTEDGAME;
            }
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
        pathAppend = new StringBuilder("FindGame/");

    }

    @Override
    public void help() {
        System.out.println("use 'e' to exit");

    }
}
