package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SelectedTeam extends States.State {
    final String pathAppend = "";
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public SelectedTeam(Role role, Connection connection, SQLstateInfo selectedInfo)
    {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        modifiableData.append(pathAppend);

        try{
            statement = connection.createStatement();
            String sql = "SELECT CONCAT('Team: ',name),\n" +
                    "CONCAT('Coach: ',first_name,' ',last_name),\n" +
                    "CONCAT('Location: ',location),\n" +
                    "CONCAT('Mascot: ',mascot) \n" +
                    "FROM Team JOIN user on coach=uid WHERE name = '"+selectedInfo.getTeam()+"';";
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("Team Info:");
            while(rs.next()){
                for(int i = 1; i < 5 ; i++){
                    System.out.println("\t"+rs.getString(i));
                }
            }
            statement = connection.createStatement();
            sql = "SELECT \n" +
                    "CONCAT(First_name, ' ', Last_name, ' Number: ', Number, ' Position(s): '),\n" +
                    "player.uid\n" +
                    " FROM player JOIN User on player.uid = user.uid WHERE team = '"+selectedInfo.getTeam()+"'";
            rs = statement.executeQuery(sql);
            ArrayList<String> roster = new ArrayList<String>();
            ArrayList<String> uids = new ArrayList<String>();
            String temp;
            int c;
            while(rs.next()) {
                roster.add(rs.getString(1));
                uids.add(rs.getString(2));
            }
            for (int i = 0; i < roster.size(); i++) {
                temp = roster.get(0);
                sql = "SELECT position FROM positions WHERE uid = '"+ uids.get(roster.indexOf(temp))+"';";
                roster.remove(0);
                rs = statement.executeQuery(sql);
                c = 0;
                while(rs.next()){
                    if (c==0){
                        temp += " "+rs.getString(1);
                    }else{
                        temp += ", "+rs.getString(1);
                    }
                    c++;
                }
                if (c == 0){
                    temp += " None";
                }
                roster.add(temp);
            }
            System.out.println("Player Roster:");
            for (String s:roster) {
                System.out.println("\t"+s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        while (true) {

            System.out.println("\n"+modifiableData);
            help();
            input = scanner.nextLine();

            //determine appropriate return type:
            if (input.equals("/b")) return null;
            else if(input.equals("/e"))return StateType.END;
            else if(input.equals("player roster")){
                try{


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
    }

    @Override
    public void help() {
        System.out.println("try:\n'/e' - to exit\n'/b' - to go back");

    }
}
