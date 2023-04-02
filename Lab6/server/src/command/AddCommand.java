package command;


import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;

import static mainCollection.SortCollection.sortCollect;

/**
 * Класс для добавления в коллекцию элемента
 */
public class AddCommand extends AncestorCommand {
    private static String description = "add {element} : добавить новый элемент в коллекцию";
    @Override
    public String getDescription() {
        return description;
    }
    /**
     * метод для добавления в коллекцию нового элемента
     */
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager) {
        SpaceMarine newElement = new SpaceMarine(objArgument.getAll());
        collectionManager.getCollectionMarines().add(newElement);
        collectionManager.updateHealthBoarders();
        sortCollect(collectionManager.getCollectionMarines());
        return new Response(ServerStatus.OK,"Элемент был успешно добавлен в коллекцию.\n");
    }
}
