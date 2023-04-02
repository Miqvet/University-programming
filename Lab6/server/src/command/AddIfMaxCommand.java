package command;


import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;

import static mainCollection.SortCollection.sortCollect;

/**
 * Класс для добавления в коллекцию макс элемента
 */

public class AddIfMaxCommand extends AncestorCommand {
    private static String description = "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager) {
        SpaceMarine newElement = new SpaceMarine(objArgument.getAll());
        if (newElement.getHealth() > collectionManager.getMaxOfHealth()){
            collectionManager.getCollectionMarines().add(newElement);
            collectionManager.updateHealthBoarders();
            sortCollect(collectionManager.getCollectionMarines());
            return new Response(ServerStatus.OK,"Элемент был успешно добавлен в коллекцию.\n");
        }
        return new Response(ServerStatus.ERROR,"Элемент не был добавлен в коллекцию, т.к. его показатель хп не самый большой.\n");
    }
}
