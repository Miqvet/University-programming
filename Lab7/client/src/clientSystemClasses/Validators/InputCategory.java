package clientSystemClasses.Validators;

import exceptions.IncorrectInputInScriptException;
import exceptions.ValidateException;
import mainCollection.collection.AstartesCategory;
import utilities.Output;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputCategory {
    public static AstartesCategory inputAndValidateCategory(Scanner scanner, boolean fileMode) throws IncorrectInputInScriptException , NoSuchElementException {
        String strCategory;
        AstartesCategory category;
        while (true) {
            try {
                if (!fileMode) Output.println(AstartesCategory.dataAstartesCategory());
                if (!fileMode) Output.println("Введите категорию из выше предложенных:");
                strCategory = scanner.nextLine().trim();
                category = AstartesCategory.getAstartesCategory(strCategory);
                break;
            } catch (ValidateException e) {
                if (fileMode) throw new IncorrectInputInScriptException();
                Output.printerror(e.getMessage());
            }
        }
        return category;
    }
}
