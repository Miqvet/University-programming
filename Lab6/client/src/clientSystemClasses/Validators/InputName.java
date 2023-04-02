package clientSystemClasses.Validators;

import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import utilities.Output;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputName {
    // fileMode == 1: работаем со скриптом вывод должен быть подавлен
    public static String askName(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException{
        String name;
        while (true) {
            try {
                if (!fileMode) Output.println("Введите имя:");
                name = scanner.nextLine().trim();
                if (name.equals("")) throw new MustBeNotEmptyException("Имя не может быть пустым!");
                break;
            } catch (NoSuchElementException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("НЕ НАДО ЛОМАТЬ ПРОГУ! Пожалуйста хватит");
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
}
