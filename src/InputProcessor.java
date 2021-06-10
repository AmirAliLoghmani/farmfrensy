import java.util.Scanner;

public class InputProcessor {
    private Manager manager;
    public InputProcessor(Manager manager) {
        this.manager = manager;
    }
    private Scanner console = new Scanner(System.in);

    public void run() {
        manager.updateEveryThing();
        boolean checking = false;

              while (true) {
            System.out.println("1 : Log in");
            System.out.println("2 : Sign Up");
            if (console.nextLine().equals("1"))
                checking = manager.loginProcess();
            else if (console.nextLine().equals("2"))
                checking = manager.signUpProcess();
            //if (checking)
              //  break;

        }

    }
}
