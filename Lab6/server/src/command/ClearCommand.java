package command;


import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;

/**
 * Класс для очистки коллекции
 */

public class ClearCommand extends AncestorCommand {
    private static String description = "clear : очистить коллекцию";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager) {
        if (!collectionManager.getCollectionMarines().isEmpty()){
            collectionManager.getCollectionMarines().clear();
            return new Response(ServerStatus.OK,"Коллекция была успешно очишена.\n");
        }
        return new Response(ServerStatus.ERROR,"Коллекция была уже пуста до выполнения команды.\n");
    }

}
