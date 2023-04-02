package clientSystemClasses.Validators;

import exceptions.IncorrectInputInScriptException;
import mainCollection.collection.Coordinates;
import utilities.Output;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputCoordinate {
    // fileMode == 1: работаем со скриптом вывод должен быть подавлен
    public static Coordinates askCoordinates(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException {
        long x;
        Integer y;
        x = askX(scanner, fileMode);
        y = askY(scanner, fileMode);
        return new Coordinates(x, y);
    }
    private static long askX(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException {
        String strX;
        long x;
        while (true) {
            try {
                if (!fileMode) Output.println("Введите координату X:");
                strX = scanner.nextLine().trim();
                x = Long.parseLong(strX);
                break;
            } catch (NoSuchElementException exception) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("Координата X не распознана!");
            } catch (NumberFormatException exception) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("Требуется ввести целое число от "+ Long.MIN_VALUE + " до " + Long.MAX_VALUE);
            } catch (NullPointerException | IllegalStateException exception) {
                Output.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }
    private static Integer askY(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException {
        String strY;
        Integer y;
        while (true) {
            try {
                if (!fileMode) Output.println("Введите координату Y:");
                strY = scanner.nextLine().trim();
                y = Integer.parseInt(strY);
                break;
            } catch (NoSuchElementException exception) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("Координата Y не распознана!");
            } catch (NumberFormatException exception) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("Требуется ввести целое число от "+ Integer.MIN_VALUE + " до " + Integer.MAX_VALUE);
            } catch (NullPointerException | IllegalStateException exception) {
                Output.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }
}
