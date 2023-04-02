import serverConnectionClasses.Server;
import serverSystemClasses.CollectionManager;


public class ServerApp {
    public static void main(String args[]){
        final String FILE_NAME = "filename";
        CollectionManager collectionManager = new CollectionManager(System.getenv(FILE_NAME));
        Server server = new Server(1234, collectionManager);
        server.serverRun();
    }
}
