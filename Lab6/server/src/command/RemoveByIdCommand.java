package command;


import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;

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
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager) {
        if (collectionManager.getCollectionMarines().isEmpty()) return new Response(ServerStatus.ERROR, "Коллекция пуста\n");
        ArrayDeque<SpaceMarine> buffer = collectionManager.getCollectionMarines().stream()
                .filter(p -> !p.getId().equals(Long.parseLong(argument)))
                .collect(Collectors.toCollection(ArrayDeque::new));
        collectionManager.setCollectionMarines(buffer);
        collectionManager.setCollectionMarines((ArrayDeque<SpaceMarine>) buffer);
        collectionManager.updateHealthBoarders();
        sortCollect(collectionManager.getCollectionMarines());
        return new Response(ServerStatus.OK,"Элемент был успешно добавлен в коллекцию.\n");
    }
}
