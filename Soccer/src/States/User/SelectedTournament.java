package States.User;

import States.Role;
import States.SQLstateInfo;
import States.StateType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SelectedTournament extends States.State {
    final String pathAppend = "";
    Scanner scanner = new Scanner(System.in);
    Statement statement;

    public SelectedTournament(Role role, Connection connection, SQLstateInfo selectedInfo) {
        super(role, connection, selectedInfo);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String input;
        modifiableData.append(pathAppend);

        try{
            statement = connection.createStatement();
            String sql = "SELECT CONCAT('Tournament: ',name),CONCAT('Location: ',Location),CONCAT('Start Date: ',start_date),\n" +
                    "CONCAT('End Date: ',end_date) FROM tournament WHERE name = '"+selectedInfo.getTournament()+"'";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                for (int i = 1; i < 5; i++){
                    System.out.println(rs.getString(i));
                }
            }
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        while (true) {

            System.out.println(modifiableData);
            help();
            input = scanner.nextLine();

            //determine appropriate return type:
            if (input.equals("/b")) return null;
            else if(input.equals("/e"))return StateType.END;
            else if (super.getRole() == Role.REFEREE && input.equals("add game")) return StateType.ADDGAME;
            else if (input.equals("find game")) return StateType.FINDGAME;
        }
    }

    @Override
    public void undoDataWrite(StringBuilder modifiableData) {
        modifiableData.reverse().delete(0, pathAppend.length()).reverse();
    }

    @Override
    public void help() {
        System.out.println("try:\n'add game'\n'find game'\n'/e' - to exit\n'/b' - to go back");

    }
}
