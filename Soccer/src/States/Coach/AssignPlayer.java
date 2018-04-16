package States.Coach;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AssignPlayer extends States.State {
    String pathAppend = "AssignPlayer/";
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public AssignPlayer(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println("\n"+modifiableData);
            String team;
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT NAME FROM TEAM WHERE COACH = '"+selectedInfo.getUser()+"'");
                rs.next();
                team = rs.getString(1);
                System.out.print("Add player "+selectedInfo.getPlayer()+" to your team, "+team+"? (y/n): ");
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
            input = scanner.nextLine();
            if(input.equals("n")) return null;
            try{
                statement = connection.createStatement();
                statement.executeUpdate("UPDATE PLAYER SET TEAM = '"+team+"' WHERE UID = '"+selectedInfo.getPlayer()+"' ");
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.print("Player was assigned to your team successfully");
            return null;
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
