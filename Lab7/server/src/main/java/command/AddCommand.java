package command;


import database.DatabaseHandleInsert;
import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;

import transmitted.ServerStatus;
import transmitted.User;

import java.sql.SQLException;

import static mainCollection.SortCollection.sortCollect;

/**
 * Класс для добавления в коллекцию элемента
 */
public class AddCommand extends AncestorCommand {
    private static final String description = "add {element} : добавить новый элемент в коллекцию";
    @Override
    public String getDescription() {
        return description;
    }
    /**
     * метод для добавления в коллекцию нового элемента
     */
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager, User user) {
        String info;
        try {
            SpaceMarine marine =
                    DatabaseHandleInsert.insertMarine(collectionManager.getDatabaseHandleConnect(),objArgument,user);
            info = "Элемент был успешно добавлен в коллекцию. \n";
            collectionManager.getCollectionMarines().add(marine);
        } catch (SQLException e) {
            return new Response(ServerStatus.REQUEST_ERROR, "При добавлении элемента в коллекцию произошла ошибка повторите попытку. \n");
        }
        collectionManager.updateHealthBoarders();
        sortCollect(collectionManager.getCollectionMarines());
        return new Response(ServerStatus.OK,info);
    }
}
