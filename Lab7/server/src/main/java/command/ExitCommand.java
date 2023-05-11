package command;


import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;
import transmitted.User;


/**
 * Команда выхода
 */

public class ExitCommand extends AncestorCommand {
    private static String description = "exit : завершить программу (без сохранения в файл)";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager, User user) {
        return new Response(ServerStatus.SERVER_DISABLED,"");
    }

}
