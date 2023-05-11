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
 * Класс для добавления в коллекцию макс элемента
 */

public class AddIfMaxCommand extends AncestorCommand {
    private static final String description = "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager, User user) {
        String info;
        if (objArgument.health() > collectionManager.getMaxOfHealth()){
            try {
                SpaceMarine marine =
                        DatabaseHandleInsert.insertMarine(collectionManager.getDatabaseHandleConnect(),objArgument,user);
                info = "Элемент был успешно добавлен в коллекцию. \n";
                collectionManager.getCollectionMarines().add(marine);
            } catch (SQLException e) {
                info = "При добавлении элемента в коллекцию произошла ошибка повторите попытку. \n";
            }
            collectionManager.updateHealthBoarders();
            sortCollect(collectionManager.getCollectionMarines());
            return new Response(ServerStatus.OK,info);
        }
        return new Response(ServerStatus.OK,"Элемент не был добавлен в коллекцию т.к. его показатель хп не самый большой.\n");
    }
}
