package States;

import java.sql.Connection;

public abstract class State {

    public static String pathAppend;

    public final static String HELP_MSG = "";

    private Role userRole;
    private Connection connection;

    public State(Role role, Connection connection){
        this.userRole = role;
        this.connection = connection;
    }

    public abstract StateType exec(StringBuilder modifiableData);

    public abstract void undoDataWrite(StringBuilder modifiableData);

    public abstract void help();

    public Role getRole(){
        return userRole;
    }

    protected void setRole(Role role){
        this.userRole = role;
    }
}
