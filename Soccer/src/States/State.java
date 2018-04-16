package States;

import java.sql.Connection;

public abstract class State {

    public static String pathAppend;
    
    protected Role userRole;
    protected Connection connection;
    protected SQLstateInfo selectedInfo;

    public State(Role role, Connection connection, SQLstateInfo selectedInfo){
        this.userRole = role;
        this.connection = connection;
        this.selectedInfo = selectedInfo;

    }

    public abstract StateType exec(StringBuilder modifiableData);

    public abstract void undoDataWrite(StringBuilder modifiableData);

    public abstract void help();

    public Role getRole(){
        return userRole;
    }

    public SQLstateInfo getSelectedInfo(){
      return this.selectedInfo;
    }

    protected void setRole(Role role){
        this.userRole = role;
    }

    public Connection getConnection() {
        return connection;
    }
}
