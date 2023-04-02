package command;


import mainCollection.collection.AstartesCategory;
import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;

import java.util.stream.Collectors;

/**
 * Команда сортировки по Категории судна
 */
public class FilterLessThanCategoryCommand extends AncestorCommand {
    private static String description = "filter_less_than_category category : вывести элементы, значение поля category которых меньше заданного";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager) {
        AstartesCategory test = AstartesCategory.getAstartesCategory(argument);
        if (collectionManager.getCollectionMarines().isEmpty()) return new Response(ServerStatus.ERROR, "Коллекция пуста\n");
        String filterCategory = collectionManager.getCollectionMarines().stream()
                .filter(p -> p.getCategory().ordinal() < test.ordinal())
                .map(SpaceMarine::toString)
                .collect(Collectors.joining("\n"));
        return new Response(ServerStatus.OK,filterCategory);
    }
}
