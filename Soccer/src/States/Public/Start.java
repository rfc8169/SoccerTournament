package States.Public;

public class Start extends States.State {
    @Override
    public void exec() {
        System.out.println("Would you like to Create an Account or Login to an existing account? (C/L)");
        String account = scanner.next();
        if(account.contains("C") || account.contains("c")){
            CreateUser user = new CreateUser();
            user.exec();
        }
        else if(account.contains("L") || account.contains("l")){
            Login login = new Login();
            login.exec();
        }
    }

    @Override
    public void help() {

    }
}
