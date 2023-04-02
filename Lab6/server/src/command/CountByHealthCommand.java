package command;


import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;

import static java.lang.Long.parseLong;
/**
 * Класс для подсчёта по хп
 */
public class CountByHealthCommand extends AncestorCommand {
    private static String description = "count_by_health health : вывести количество элементов, значение поля health которых равно заданному";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager) {
        if (collectionManager.getCollectionMarines().isEmpty()) return new Response(ServerStatus.ERROR, "Коллекция пуста\n");
        long count = collectionManager.getCollectionMarines().stream()
                .filter(p -> p.getHealth() == Long.parseLong(argument))
                .count();
        return new Response(ServerStatus.OK,"В данный момент в коллекции " + count + " элемента с количеством хп равным заданному\n");
    }
}
