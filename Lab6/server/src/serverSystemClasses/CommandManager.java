package serverSystemClasses;


import command.*;

import java.util.HashMap;

/**
 * Класс отвечающий за создание и обработку коллекции команд
 * создаётся только 1 раз
 * */
public class CommandManager {
    private static final HashMap<String, AncestorCommand> commands = new HashMap<>();
    static {
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("add", new AddCommand());
        commands.put("update", new UpdateCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("clear", new ClearCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("exit", new ExitCommand());
        commands.put("add_if_max", new AddIfMaxCommand());
        commands.put("add_if_min", new AddIfMinCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("count_by_health", new CountByHealthCommand());
        commands.put("filter_less_than_category", new FilterLessThanCategoryCommand());
        commands.put("print_field_descending_health", new PrintFieldDescendingHealthCommand());
    }
    public static HashMap<String, AncestorCommand> getCommands(){
        return commands;
    }
}
