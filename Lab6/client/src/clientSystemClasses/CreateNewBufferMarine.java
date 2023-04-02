package clientSystemClasses;

import clientSystemClasses.Validators.*;
import exceptions.IncorrectInputInScriptException;
import transmitted.MarinBuffer;

import java.util.Scanner;

public class CreateNewBufferMarine {
    public static MarinBuffer generateMarineAdd(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException {
        return new MarinBuffer(

                InputName.askName(scanner, fileMode),
                InputHealth.askHealth(scanner,fileMode),
                InputCoordinate.askCoordinates(scanner,fileMode),
                InputCategory.askCategory(scanner,fileMode),
                InputWeapon.askWeaponType(scanner,fileMode),
                InputMeleeWeapon.askWeaponType(scanner,fileMode),
                InputChapter.askChapter(scanner, fileMode)
        );
    }
}
