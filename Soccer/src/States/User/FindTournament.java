package States.User;

import States.Role;
import States.StateType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class FindTournament extends States.State {
    StringBuilder pathAppend = new StringBuilder("FindTournament/");
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public FindTournament(Role role, Connection connection) {
        super(role, connection);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input, name;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println(modifiableData);


            try{
                statement = connection.createStatement();
                String sql = "SELECT CONCAT(NAME,', ', LOCATION) FROM TOURNAMENT";
                ResultSet rs = statement.executeQuery(sql);
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
                statement.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }


            System.out.print("\nEnter the name of a specific tournament for more information: ");
            name = scanner.nextLine();


            try{
                statement = connection.createStatement();
                String sql = "SELECT CONCAT(NAME,', ', LOCATION,', ', START_DATE,', ', END_DATE) " +
                        "FROM TOURNAMENT WHERE \'"+name+"\' = NAME";
                ResultSet rs = statement.executeQuery(sql);
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
                statement.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            //potentially do some work or actions:
            //todo

            System.out.println("try 'h' for help, 'e' to end or another command");
            input = scanner.nextLine();
            //determine appropriate return type:
            if (input.equals("")) return null;
            else if(input.equals("h")) help();
            else if(input.equals("e"))return StateType.END;
            else if(input.equals("details")){
                input = "<"+input+">/";
                pathAppend.append(input);
                modifiableData.append(input);
                return StateType.SELECTEDTOURNAMENT;
            }
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
        pathAppend = new StringBuilder("FindTournament/");

    }

    @Override
    public void help() {

    }
}
