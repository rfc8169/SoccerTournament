package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class FindGame extends States.State {
    StringBuilder pathAppend = new StringBuilder("FindGame/");
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public FindGame(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String id, input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println(modifiableData);

            try{
                statement = connection.createStatement();
                String sql = "SELECT CONCAT(GAME_ID,', ', LOCATION,', ', HOME_TEAM,', ', AWAY_TEAM, ', ', TOURNAMENT) FROM GAME";
                ResultSet rs = statement.executeQuery(sql);
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
                statement.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            System.out.println("\nIf you would like to find a specific game enter its game id: ");
            id = scanner.nextLine();

            //potentially do some work or actions:
            //todo

//            if(input.equals("h")){
//                help();
//                return StateType.FINDGAME;
//            }
//            else if(input.equals("e"))return StateType.END;
//            else{
//                input = "<"+input+">/";
//                pathAppend.append(input);
//                modifiableData.append(input);
//                return StateType.SELECTEDGAME;
//            }


            try{
                statement = connection.createStatement();
                String sql = "SELECT CONCAT(GAME_ID,', ', START_TIME,', ', END_TIME,', ', FIELD_NO,', ', LOCATION,', '," +
                        " HOME_TEAM,', ', AWAY_TEAM, ', ', TOURNAMENT) FROM GAME WHERE \'"+id+"\' = GAME_ID";
                ResultSet rs = statement.executeQuery(sql);
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
                statement.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            System.out.println("try 'h' for help, 'e' to end or another command");
            input = scanner.nextLine();

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
    }
}
