package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SelectedPlayer extends States.State {
    final String pathAppend = "";
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public SelectedPlayer(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.println(modifiableData);
            try {
                statement = connection.createStatement();
                String sql = "SELECT CONCAT(First_Name,' ',Last_Name),CONCAT('Team: ',Team),CONCAT('Number: ',Number)\n" +
                        "FROM Player JOIN User ON player.uid=user.uid WHERE player.uid='"+selectedInfo.getPlayer()+"';";
                ResultSet rs = statement.executeQuery(sql);
                while(rs.next()){
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getString(2));
                    System.out.println(rs.getString(3));
                }
                sql = "SELECT position FROM positions WHERE uid = '"+selectedInfo.getPlayer()+"';";
                rs = statement.executeQuery(sql);
                System.out.println("Psotions:");
                while(rs.next()){
                    System.out.println("\t"+rs.getString(1));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("try 'h' for help");
            input = scanner.nextLine();


            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (input.equals("")) return null;
            else if(input.equals("h")) help();
            else if(input.equals("e"))return StateType.END;
            else if(input.equals("player stats")){
                try{
                    statement = connection.createStatement();
                    String sql = String.format("SELECt CONCAT('Goals: ',(\n" +
                            "SELECT COUNT(event) FROM statistics WHERE event = 'Goal' AND uid = '%s'\n" +
                            ")) as goals,\n" +
                            "CONCAT('Asists: ',(\n" +
                            "SELECT COUNT(event) FROM statistics WHERE event = 'Assist' AND uid = '%s'\n" +
                            ")) as assists,\n" +
                            "CONCAT('Saves: ',(\n" +
                            "SELECT COUNT(event) FROM statistics WHERE event = 'Save' AND uid = '%s'\n" +
                            ")) as saves,\n" +
                            "CONCAT('Penalties: ',(\n" +
                            "SELECT COUNT(event) FROM statistics WHERE event = 'Penalty' AND uid = '%s'\n" +
                            ")) as penalties,\n" +
                            "CONCAT('Red Cards: ',(\n" +
                            "SELECT COUNT(event) FROM statistics WHERE event = 'Red Card' AND uid = '%s'\n" +
                            ")) as red_cards,\n" +
                            "CONCAT('Yellow Cards: ',(\n" +
                            "SELECT COUNT(event) FROM statistics WHERE event = 'Yellow Card' AND uid = '%s'\n" +
                            ")) as yellow_Cards;",selectedInfo.getPlayer(),selectedInfo.getPlayer(),
                            selectedInfo.getPlayer(),selectedInfo.getPlayer(),selectedInfo.getPlayer(),
                            selectedInfo.getPlayer());
                    ResultSet rs = statement.executeQuery(sql);
                    while(rs.next()){
                        for (int i = 1; i < 7 ; i++){
                            System.out.println(rs.getString(i));
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            else if(input.equals("add to team") && super.getRole() == Role.COACH) return StateType.ASSIGNPLAYER;
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
    }

    @Override
    public void help() {
        System.out.println("try:\n'add to team'\n'e' - to exit");

    }
}
