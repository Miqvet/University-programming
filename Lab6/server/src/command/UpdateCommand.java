package command;

import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class UpdateCommand extends AncestorCommand {
    private static String description = "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager) {
        if (collectionManager.getCollectionMarines().isEmpty()) return new Response(ServerStatus.ERROR, "Коллекция пуста\n");
        ArrayDeque<SpaceMarine> asd = collectionManager.getCollectionMarines().stream()
                .map(p -> {
                    if (p.getId().equals(Long.parseLong(argument))) {
                        return new SpaceMarine(objArgument.getAll());
                    } else {
                        return p;
                    }
                })
                .collect(Collectors.toCollection(ArrayDeque::new));
        collectionManager.setCollectionMarines(asd);
        collectionManager.updateHealthBoarders();
        return new Response(ServerStatus.OK,"Элемент коллекции был успешно обновлён.\n");
    }
}


