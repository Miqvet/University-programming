import clientConnectionClasses.Client;
import clientSystemClasses.UserManager;
import userHandler.AuthHandler;
import utilities.Output;

import java.util.Scanner;

public class ClientApp2 {
    public static void main(String[] args){
        Scanner userScanner = new Scanner(System.in);
        UserManager userHandler = new UserManager(userScanner);
        AuthHandler authHandler = new AuthHandler(userScanner);
        int RECONNECTION_TIME_OUT = 1000; // время повторного запроса 1 сек
        int MAX_RECONNECTION_ATTEMPTS = 15; // Количество повторных запросов
        String strPort = null;
        int port = -1;
        // Такие числа, потому что нет смысла ждать больше 15 сек
        Output.println("Создайте переменную окружения Port_Value и введите с её помощью значение порта для установления соединения");
        strPort =  System.getenv("Port_Value");
        try {
            port = Integer.parseInt(strPort);
            Client client = new Client("localhost", port, userHandler, RECONNECTION_TIME_OUT, MAX_RECONNECTION_ATTEMPTS, authHandler);
            client.ClientRun();
            userScanner.close();
        } catch(NumberFormatException e){
            Output.printerror("Порт должен быть целым числом больше 0");
        }
    }
}
