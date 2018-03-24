public interface StateNode {

    public StateNode last;
    public final static String HELP_MSG = "";
    public Role userRole;

    public void prompt();

    public void help();
}
