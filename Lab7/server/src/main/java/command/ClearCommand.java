package command;


import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;
import transmitted.User;

/**
 * Класс для очистки коллекции
 */

public class ClearCommand extends AncestorCommand {
    private static final String description = "clear : очистить коллекцию";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager, User user) {
        if (!collectionManager.getCollectionMarines().isEmpty()){
            collectionManager.getCollectionMarines().clear();
            return new Response(ServerStatus.OK,"Коллекция была успешно очишена.\n");
        }
        return new Response(ServerStatus.OK,"Коллекция была уже пуста до выполнения команды.\n");
    }

}
