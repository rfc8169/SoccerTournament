public abstract class State {

    private State last;
    public final static String HELP_MSG = "";
    private Role userRole;

    public State(){

    }

    public abstract void exec();

    public abstract void help();
}
