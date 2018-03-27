package States;

import java.util.Scanner;

public abstract class State {

    private State last;
    public final static String HELP_MSG = "";
    private States.Public.Role userRole;
    public Scanner scanner;

    public State(){
        scanner = new Scanner(System.in);
    }

    public abstract void exec();

    public abstract void help();

    public void close() {
        scanner.close();
    }
}
