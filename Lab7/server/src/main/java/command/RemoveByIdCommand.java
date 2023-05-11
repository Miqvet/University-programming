package command;


import database.DatabaseHandleRemove;
import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;
import transmitted.User;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.stream.Collectors;

import static mainCollection.SortCollection.sortCollect;


/**
 * Класс для удаления по id
 */
public class RemoveByIdCommand extends AncestorCommand {
    private static String description = "remove_by_id id : удалить элемент из коллекции по его id";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager, User user) {
        if (collectionManager.getCollectionMarines().isEmpty()) return new Response(ServerStatus.OK, "Коллекция пуста\n");
        SpaceMarine buffer1 = collectionManager.getById(Long.parseLong(argument));
        try{
            if (buffer1 != null && buffer1.getLogin().equals(user.getLogin())){
                ArrayDeque<SpaceMarine> buffer = collectionManager.getCollectionMarines().stream()
                        .filter(p -> !p.getId().equals(Long.parseLong(argument)))
                        .collect(Collectors.toCollection(ArrayDeque::new));
                collectionManager.setCollectionMarines(buffer);
                DatabaseHandleRemove.removeMarine(collectionManager.getDatabaseHandleConnect(),Long.parseLong(argument));
                collectionManager.updateHealthBoarders();
                sortCollect(collectionManager.getCollectionMarines());
                return new Response(ServerStatus.OK,"Элемент был успешно удалён из коллекции.\n");
            }
            else if(buffer1 == null){
                return new Response(ServerStatus.OK,"Элемента с этим ID нет в коллекции.\n");
            }
            else{
                return new Response(ServerStatus.OK,"Вы не являетесь владельцем этого элемента, " +
                        "поэтому не можете его модифицировать.\n");
            }
        } catch (SQLException e){
            return new Response(ServerStatus.OK,"Во время удаления произошёл разрыв соединения с бд попробуйте позднее \n");
        }

    }
}
