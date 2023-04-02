package command;


import serverSystemClasses.CollectionManager;
import serverSystemClasses.CommandManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Команда для вывода help
 */
public class HelpCommand extends AncestorCommand {
    private static String description = "help : вывести справку по доступным командам";
    @Override
    public String getDescription() {
        return description;
    }
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager) {
        List<AncestorCommand> valuesList = new ArrayList<>(CommandManager.getCommands().values());
        String dataHelp = valuesList.stream()
                .map(AncestorCommand::getDescription)
                .collect(Collectors.joining("\n"));
        dataHelp += "\n";
        return new Response(ServerStatus.OK, dataHelp);
    }
}
