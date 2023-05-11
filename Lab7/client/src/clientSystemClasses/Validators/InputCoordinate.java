package clientSystemClasses.Validators;

import exceptions.IncorrectInputInScriptException;
import mainCollection.collection.Coordinates;
import utilities.Output;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputCoordinate {
    // fileMode == 1: работаем со скриптом вывод должен быть подавлен
    public static Coordinates inputCoordinates(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException, NoSuchElementException {
        long x;
        Integer y;
        x = inputAndValidateX(scanner, fileMode);
        y = inputAndValidateY(scanner, fileMode);
        return new Coordinates(x, y);
    }
    private static long inputAndValidateX(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException, NoSuchElementException {
        String strX;
        long x;
        while (true) {
            try {
                if (!fileMode) Output.println("Введите координату X:");
                strX = scanner.nextLine().trim();
                x = Long.parseLong(strX);
                break;
            } catch (NumberFormatException exception) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("Требуется ввести целое число от " + Long.MIN_VALUE + " до " + Long.MAX_VALUE);
            }
        }
        return x;
    }
    private static Integer inputAndValidateY(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException, NoSuchElementException {
        String strY;
        Integer y;
        while (true) {
            try {
                if (!fileMode) Output.println("Введите координату Y:");
                strY = scanner.nextLine().trim();
                y = Integer.parseInt(strY);
                break;
            } catch (NumberFormatException exception) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("Требуется ввести целое число от "+ Integer.MIN_VALUE + " до " + Integer.MAX_VALUE);
            }
        }
        return y;
    }
}
