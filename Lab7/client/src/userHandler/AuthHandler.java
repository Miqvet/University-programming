package userHandler;

import transmitted.Request;
import transmitted.User;

import java.util.Scanner;

/**
 * Handle user login and password.
 */
public class AuthHandler {
    private final String loginCommand = "check_user";
    private final String registerCommand = "register_user";

    private Scanner userScanner;

    public AuthHandler(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * Handle user authentication.
     *
     * @return Request of user.
     */
    public Request initializedUser(){
        AuthAsker authAsker = new AuthAsker(userScanner);
        String command = authAsker.askQuestion("У вас уже есть учетная запись?") ? loginCommand : registerCommand;
        User user = new User(authAsker.askLogin(), authAsker.askPassword());
        return new Request(command, "", null, user);
    }
}
