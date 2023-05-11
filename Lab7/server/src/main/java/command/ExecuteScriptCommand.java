package command;


import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;
import transmitted.User;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Класс для выполнения скрипта
 */
public class ExecuteScriptCommand extends AncestorCommand {
    private static String description = "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager, User user) {
        return new Response(ServerStatus.OK, "Начинаю исполнение скрипта "+ argument);
    }
}
