package command;


import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;

import java.util.ArrayDeque;
import java.util.stream.Collectors;


public class RemoveGreaterCommand extends AncestorCommand {
    private static String description = "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager){
        if (collectionManager.getCollectionMarines().isEmpty()) return new Response(ServerStatus.ERROR, "Коллекция пуста\n");
        collectionManager.setCollectionMarines(collectionManager.getCollectionMarines().stream()
                .filter(p -> p.compareTo(new SpaceMarine(objArgument.getAll())) <=   0)
                .collect(Collectors.toCollection(ArrayDeque::new)));

        collectionManager.updateHealthBoarders(); // Метод для обновления нижний и верхних границ хп, т.к. было удаление элемента возможно необходимо обновить границы
        return new Response(ServerStatus.OK, "Элементы успешно удалены\n");
    }
}
