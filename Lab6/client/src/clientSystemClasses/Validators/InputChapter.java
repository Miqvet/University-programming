package clientSystemClasses.Validators;

import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import mainCollection.collection.Chapter;
import utilities.Output;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputChapter {
    public static Chapter askChapter(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException {
        String name;
        String world;
        name = askChapterName(scanner, fileMode);
        world = askChapterWorld(scanner, fileMode);
        return new Chapter(name, world);
    }
    // fileMode == 1: работаем со скриптом вывод должен быть подавлен
    private static String askChapterName(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException{
        String name;
        while (true) {
            try {
                if (!fileMode) Output.println("Введите имя главы судна:");
                name = scanner.nextLine().trim();
                if (name.equals("")) throw new MustBeNotEmptyException("Имя не может быть пустым!");
                break;
            } catch (NoSuchElementException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("Повторите попытку имя не распознано");
            } catch (MustBeNotEmptyException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror(e.getMessage());
            } catch (IllegalStateException e) {
                Output.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }
    private static String askChapterWorld(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException{
        String world;
        while (true) {
            try {
                if (!fileMode) Output.println("Введите мир главы судна:");
                world = scanner.nextLine().trim();
                if (world.equals("")) throw new MustBeNotEmptyException("Мир не может быть пустым!");
                break;
            } catch (NoSuchElementException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("Повторите попытку мир не распознан");
            } catch (MustBeNotEmptyException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror(e.getMessage());
            } catch (IllegalStateException e) {
                Output.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return world;
    }
}
