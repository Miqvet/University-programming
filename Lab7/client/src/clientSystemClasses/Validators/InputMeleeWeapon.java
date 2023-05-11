package clientSystemClasses.Validators;

import exceptions.IncorrectInputInScriptException;
import exceptions.ValidateException;
import mainCollection.collection.MeleeWeapon;
import mainCollection.collection.Weapon;
import utilities.Output;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputMeleeWeapon {
    public static MeleeWeapon inputAndValidateWeaponType(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException, NoSuchElementException {
        String strMeleeWeaponType;
        MeleeWeapon meleeWeaponType;
        while (true) {
            try {
                if (!fileMode) Output.println(MeleeWeapon.dataMeleeWeapon());
                if (!fileMode) Output.println("Введите категорию из выше предложенных:");
                strMeleeWeaponType = scanner.nextLine().trim();
                meleeWeaponType = MeleeWeapon.selectMeleeWeapon(strMeleeWeaponType);
                break;
            } catch (ValidateException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror(e.getMessage());
            }
        }
        return meleeWeaponType;
    }
}
