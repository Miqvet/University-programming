package command;


import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;
import transmitted.User;

/**
 * Команда для вывода информации о коллекции
 */

public class InfoCommand extends AncestorCommand {
    private static String description = "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    @Override
    public String getDescription() {
        return description;
    }
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager, User user){
        String infoData = "";
        infoData += "\nДата создания массива = " + CollectionManager.getDateOfCollection() + "\n";
        infoData +="Размер массива = " + collectionManager.getCollectionMarines().size() + "\n";
        infoData +="Тип массива = " + collectionManager.getCollectionMarines().getClass() + "\n";
        infoData +="В коллекции хранятся элементы типа = " + SpaceMarine.class + "\n";
        return new Response(ServerStatus.OK, infoData);
    }
}
