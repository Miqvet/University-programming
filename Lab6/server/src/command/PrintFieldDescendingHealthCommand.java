package command;


import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;

import java.util.Comparator;
import java.util.stream.Collectors;

public class PrintFieldDescendingHealthCommand extends AncestorCommand {
    private static String description = "print_field_descending_health : вывести значения поля health всех элементов в порядке убывания";
    @Override
    public String getDescription() {
        return description;
    }
    /**
     * Функция получения значения поля health всех элементов в порядке убывания
     * @return ArrayList со всеми жизнями
     */

    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager) {
        if (collectionManager.getCollectionMarines().isEmpty()) return new Response(ServerStatus.ERROR, "Коллекция пуста\n");
        String sortedHealth = collectionManager.getCollectionMarines().stream()
                .sorted(Comparator.comparingLong(SpaceMarine::getHealth).reversed())
                .mapToLong(SpaceMarine::getHealth)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("\n"));
        return new Response(ServerStatus.OK,sortedHealth);
    }
}
