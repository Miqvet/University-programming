import database.*;
import serverConnectionClasses.Server;
import serverSystemClasses.CollectionManager;
import utilities.Output;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.Scanner;

public class ServerApp {
    static Scanner sc = new Scanner(System.in);
    final static String URL1 = "jdbc:postgresql://";
    final static String URL2 = ":5432/studs";
    static String LOGIN;
    static String HOST;
    static String PASSWORD;
    public static void main(String[] args){
        Output.println("Создайте переменную окружения Port_Value и введите в неё ваш порт");
        Output.println("Введите Host бд");
        HOST = sc.nextLine();
        Output.println("Введите Login бд");
        LOGIN = sc.nextLine();
        Output.println("Введите пароль бд");
        PASSWORD = sc.nextLine();
        while (true){
            try{
                DatabaseHandleConnect databaseHandler = new DatabaseHandleConnect(URL1 + HOST + URL2, LOGIN, PASSWORD);
                String strPort =  System.getenv("Port_Value");
                try {
                    int port = Integer.parseInt(strPort);;
                    (new ServerSocket(port)).close();
                    Output.println("Port " + port + " is free.");
                    CollectionManager collectionManager = new CollectionManager(databaseHandler);
                    Server server = new Server(port, collectionManager);
                    server.serverRun();
                } catch (IOException e) {
                    Output.println("Port is not free.\n" +
                            "Программа не будет запущена");
                } catch(NumberFormatException e){
                    Output.printerror("Порт должен быть целым числом больше 0");
                }
            } catch (SQLException e){
                Output.println(e.getMessage());
                Output.println("Введите Host бд");
                HOST = sc.nextLine();
                Output.println("Введите Login бд");
                LOGIN = sc.nextLine();
                Output.println("Введите пароль бд");
                PASSWORD = sc.nextLine();
            }
        }

    }

}
