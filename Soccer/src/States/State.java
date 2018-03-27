package States;

public abstract class State {

    private State last;
    public final static String HELP_MSG = "";

    private Role userRole;

    public State(Role role){
        this.userRole = role;
    }

    public abstract StateType exec(StringBuilder modifiableData);

    public abstract void undoDataWrite(StringBuilder modifiableData);

    public abstract void help();

    public Role getRole(){
        return userRole;
    }
}
