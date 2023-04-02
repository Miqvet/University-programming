package clientSystemClasses.Validators;

import exceptions.IncorrectInputInScriptException;
import exceptions.NotInDeclaredLimitsException;
import utilities.Output;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputHealth {
    private static final long MIN_HEALTH = 0;
    // fileMode == 1: работаем со скриптом вывод должен быть подавлен
    public static Long askHealth(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException {
        long health;
        String strHealth;

        while (true) {
            try {
                if (!fileMode) Output.println("Введите здоровье:");
                strHealth = scanner.nextLine().trim();
                health = Long.parseLong(strHealth);
                if (health <= MIN_HEALTH) throw new NotInDeclaredLimitsException("Здоровье должно быть больше нуля!");
                break;
            } catch (NoSuchElementException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("НЕ НАДО ЛОМАТЬ ПРОГУ! Пожалуйста хватит");
            } catch (NotInDeclaredLimitsException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror(e.getMessage());
            } catch (NumberFormatException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("Здоровье должно быть представлено целым числом больше нуля!");
            } catch (NullPointerException | IllegalStateException e) {
                Output.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return health;
    }
}
