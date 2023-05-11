package clientSystemClasses.Validators;

import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import utilities.Output;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputName {
    // fileMode == 1: работаем со скриптом вывод должен быть подавлен
    public static String inputAndValidateName(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException, NoSuchElementException{
        String name;
        while (true) {
            try {
                if (!fileMode) Output.println("Введите имя:");
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
}
