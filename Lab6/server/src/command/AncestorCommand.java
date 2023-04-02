package command;

import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;


/**
 * Класс являющийся наследником всем команд
 * В нём прописаны метод исполнения и аргумент команды
 * */
public abstract class AncestorCommand {
    private static String description;
    public abstract Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager);
    public abstract String getDescription();
}
