package clientSystemClasses.Validators;

import exceptions.IncorrectInputInScriptException;
import exceptions.ValidateException;
import mainCollection.collection.Weapon;
import utilities.Output;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputWeapon  {
    public static Weapon askWeaponType(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException {
        String strWeaponType;
        Weapon weaponType;
        while (true) {
            try {
                if (!fileMode) Output.println(Weapon.dataWeapen());
                if (!fileMode) Output.println("Введите категорию из выше предложенных:");
                strWeaponType = scanner.nextLine().trim();
                weaponType = Weapon.selectWeapon(strWeaponType);
                break;
            } catch (NoSuchElementException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("Оружие не распознано!");
            } catch (ValidateException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror(e.getMessage());
            } catch (IllegalStateException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return weaponType;
    }
}
