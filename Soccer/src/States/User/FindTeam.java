package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class FindTeam extends States.State {
    StringBuilder pathAppend = new StringBuilder("FindTeam/");
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public FindTeam(Role role, Connection connection, SQLstateInfo selectedInfo)
    {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println(modifiableData);
            try{
                statement = connection.createStatement();
                String sql = "SELECT NAME FROM TEAM;";
                ResultSet rs = statement.executeQuery(sql);
                System.out.println("Teams:");
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("try 'h' for help");
            System.out.print("enter team: ");
            input = scanner.nextLine();

            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
            else if(input.equals("h")) help();
            else if(input.equals("e"))return StateType.END;
            else {
                try{
                    statement = connection.createStatement();
                    String sql = "SELECT '"+input+"' IN (SELECT Name FROM Team)";
                    ResultSet rs = statement.executeQuery(sql);
                    rs.next();
                    if(rs.getString(1).equals("TRUE")){
                        selectedInfo.setTeam(input);
                        input = "<"+input+">/";
                        pathAppend.append(input);
                        modifiableData.append(input);
                        return StateType.SELECTEDTEAM;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
        pathAppend = new StringBuilder("FindTeam/");

    }

    @Override
    public void help() {
        System.out.println("use 'e' to exit");

    }
}
