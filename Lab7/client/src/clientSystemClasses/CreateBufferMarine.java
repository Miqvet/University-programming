package clientSystemClasses;

import clientSystemClasses.Validators.*;
import exceptions.IncorrectInputInScriptException;
import transmitted.MarinBuffer;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreateBufferMarine {
    public static MarinBuffer generateMarineAdd(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException, NoSuchElementException {
        return new MarinBuffer(
                InputName.inputAndValidateName(scanner, fileMode),
                InputHealth.inputAndValidateHealth(scanner,fileMode),
                InputCoordinate.inputCoordinates(scanner,fileMode),
                InputCategory.inputAndValidateCategory(scanner,fileMode),
                InputWeapon.inputAndValidateWeaponType(scanner,fileMode),
                InputMeleeWeapon.inputAndValidateWeaponType(scanner,fileMode),
                InputChapter.inputChapter(scanner, fileMode)
        );
    }
}
