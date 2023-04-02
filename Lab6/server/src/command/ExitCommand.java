package command;


import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;

import static document.WriteToDocument.writeToXML;

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
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager) {
        writeToXML(collectionManager);
        return new Response(ServerStatus.SERVER_DISABLED,"Сервер отключён\n");
    }

}
