import clientConnectionClasses.Client;
import clientSystemClasses.UserManager;

import java.io.IOException;
import java.util.Scanner;

public class ClientApp {
    public static void main(String args[]){
        Scanner userScanner = new Scanner(System.in);
        UserManager userHandler = new UserManager(userScanner);
        Client client = new Client("localhost", 1234, userHandler);
        try {
            client.ClientRun();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userScanner.close();
    }
}
