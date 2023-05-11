package command;

import database.DatabaseHandleRemove;
import database.DatabaseHandleUpdate;
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

public class UpdateCommand extends AncestorCommand {
    private static String description = "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager, User user) {
        if (collectionManager.getCollectionMarines().isEmpty()) return new Response(ServerStatus.OK, "Коллекция пуста\n");

        SpaceMarine buffer1 = collectionManager.getById(Long.parseLong(argument));
        try{
            if (buffer1 != null && buffer1.getLogin().equals(user.getLogin()) && objArgument != null){
                ArrayDeque<SpaceMarine> asd = collectionManager.getCollectionMarines().stream()
                        .map(p -> {
                            if (p.getId().equals(Long.parseLong(argument))) {
                                return new SpaceMarine(objArgument.getAll(),Long.parseLong(argument),user.getLogin());
                            } else {
                                return p;
                            }
                        })
                        .collect(Collectors.toCollection(ArrayDeque::new));
                DatabaseHandleUpdate.updateMarine(collectionManager.getDatabaseHandleConnect(),objArgument,user , Long.parseLong(argument));
                collectionManager.setCollectionMarines(asd);
                collectionManager.updateHealthBoarders();
                return new Response(ServerStatus.OK,"Элемент коллекции был успешно обновлён.\n");
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


