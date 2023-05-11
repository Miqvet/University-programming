package command;


import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;
import transmitted.User;

import java.util.ArrayDeque;
import java.util.stream.Collectors;


public class RemoveGreaterCommand extends AncestorCommand {
    //TODO: надо переделать под бд
    private static String description = "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager, User user){
        if (collectionManager.getCollectionMarines().isEmpty()) return new Response(ServerStatus.OK, "Коллекция пуста\n");
        collectionManager.setCollectionMarines(collectionManager.getCollectionMarines().stream()
                .filter(p -> p.compareTo(new SpaceMarine(objArgument.getAll(),Long.parseLong(argument),user.getLogin())) <=   0)
                .collect(Collectors.toCollection(ArrayDeque::new)));

        collectionManager.updateHealthBoarders(); // Метод для обновления нижний и верхних границ хп, т.к. было удаление элемента возможно необходимо обновить границы
        return new Response(ServerStatus.OK, "Элементы успешно удалены\n");
    }
}
