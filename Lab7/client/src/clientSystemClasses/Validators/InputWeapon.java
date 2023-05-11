package clientSystemClasses.Validators;

import exceptions.IncorrectInputInScriptException;
import exceptions.ValidateException;
import mainCollection.collection.Weapon;
import utilities.Output;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputWeapon  {
    public static Weapon inputAndValidateWeaponType(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException, NoSuchElementException {
        String strWeaponType;
        Weapon weaponType;
        while (true) {
            try {
                if (!fileMode) Output.println(Weapon.dataWeapen());
                if (!fileMode) Output.println("Введите категорию из выше предложенных:");
                strWeaponType = scanner.nextLine().trim();
                weaponType = Weapon.selectWeapon(strWeaponType);
                break;
            } catch (ValidateException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror(e.getMessage());
            }
        }
        return weaponType;
    }
}
