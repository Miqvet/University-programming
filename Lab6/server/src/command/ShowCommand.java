package command;


import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShowCommand extends AncestorCommand {
    private static String description = "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager) {
        if (collectionManager.getCollectionMarines().isEmpty()) return new Response(ServerStatus.ERROR, "Коллекция пуста");
        List<SpaceMarine> showList = new ArrayList<>(collectionManager.getCollectionMarines());
        String dataShow = showList.stream()
                .map(SpaceMarine::toString)
                .collect(Collectors.joining("\n"));
        dataShow += "\n";
        return new Response(ServerStatus.OK, dataShow);
    }
}

