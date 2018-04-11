package States.Public;

import States.Role;
import States.StateType;

import java.util.Scanner;

public class Login extends States.State {
    StringBuilder pathAppend = new StringBuilder("Login/");
    Scanner scanner = new Scanner(System.in);

    public Login(Role role) {
        super(role);
    }

    @Override
    public StateType exec(StringBuilder modifiableData) {
        String inputName;
        String inputRole;
        //temporarily using to track state path as example
        modifiableData.append(pathAppend);
        while (true) {

            System.out.print("Login name: ");
            inputName = scanner.nextLine();
            System.out.println("{enter role for testing (u/c/r)");
            inputRole = scanner.nextLine();

            //potentially do some work or actions:
            //todo

            //determine appropriate return type:
            if (inputName.equals("") || inputRole.equals("")) return null;
            else{
                inputName = "<"+inputName+">/";
                pathAppend.append(inputName);
                modifiableData.append(inputName);
                if(inputRole.equals("c")) super.setRole(Role.COACH);
                else if(inputRole.equals("r")) super.setRole(Role.REFEREE);
                else super.setRole(Role.USER);
                return StateType.LOGGEDIN;
            }
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
