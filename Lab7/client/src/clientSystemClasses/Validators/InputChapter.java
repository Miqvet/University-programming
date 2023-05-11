package clientSystemClasses.Validators;

import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import mainCollection.collection.Chapter;
import utilities.Output;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputChapter {
    public static Chapter inputChapter(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException, NoSuchElementException {
        String name;
        String world;
        name = inputAndValidateChapterName(scanner, fileMode);
        world = inputAndValidateChapterWorld(scanner, fileMode);
        return new Chapter(name, world);
    }
    // fileMode == 1: работаем со скриптом вывод должен быть подавлен
    private static String inputAndValidateChapterName(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException, NoSuchElementException{
        String name;
        while (true) {
            try {
                if (!fileMode) Output.println("Введите имя главы судна:");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) throw new MustBeNotEmptyException("Имя не может быть пустым!");
                break;
            } catch (MustBeNotEmptyException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror(e.getMessage());
            }
        }
        return name;
    }
    private static String inputAndValidateChapterWorld(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException{
        String world;
        while (true) {
            try {
                if (!fileMode) Output.println("Введите мир главы судна:");
                world = scanner.nextLine().trim();
                if (world.isEmpty()) throw new MustBeNotEmptyException("Мир не может быть пустым!");
                break;
            } catch (MustBeNotEmptyException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror(e.getMessage());
            }
        }
        return world;
    }
}
