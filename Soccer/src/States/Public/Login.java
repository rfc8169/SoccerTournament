package States.Public;

public class Login extends States.State {

    @Override
    public void exec() {
        System.out.println("Enter your username: ");
        String username = scanner.next();

    }

    @Override
    public void help() {

    }
}
