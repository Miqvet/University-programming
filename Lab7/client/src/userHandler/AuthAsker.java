package userHandler;


import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;
import utilities.Output;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Asks user a login and password.
 */
public class AuthAsker {
    private Scanner userScanner;

    public AuthAsker(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * Asks user a login.
     *
     * @return login.
     */
    public String askLogin() {
        String login;
        while (true) {
            try {
                Output.print("Введите логин:");
                login = userScanner.nextLine().trim();
                if (login.equals("")) throw new MustBeNotEmptyException("Имя не может быть пустым!");
                break;
            } catch (NoSuchElementException exception) {
                Output.printerror("Данного логина не существует!");
            } catch (MustBeNotEmptyException exception) {
                Output.printerror(exception.getMessage());
            } catch (IllegalStateException exception) {
                Output.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return login;
    }

    /**
     * Asks user a password.
     *
     * @return password.
     */
    public String askPassword() {
        String password;
        while (true) {
            try {
                Output.print("Введите пароль:");
                password = userScanner.nextLine().trim();
                break;
            } catch (NoSuchElementException exception) {
                Output.printerror("Неверный логин или пароль!");
            } catch (IllegalStateException exception) {
                Output.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return password;
    }

    /**
     * Asks a user a question.
     *
     * @param question A question.
     * @return Answer (true/false).
     */
    public boolean askQuestion(String question) {
        String finalQuestion = question + " | true or false.";
        String answer;
        while (true) {
            try {
                Output.println(finalQuestion);
                answer = userScanner.nextLine().trim();
                if (!answer.equals("true") && !answer.equals("false")) throw new NotInDeclaredLimitsException("Ответ должен быть представлен знаками 'true' или 'false'!");
                break;
            } catch (NoSuchElementException exception) {
                Output.printerror("Ответ не распознан!");
            } catch (NotInDeclaredLimitsException exception) {
                Output.printerror(exception.getMessage());
            } catch (IllegalStateException exception) {
                Output.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return answer.equals("true");
    }
}
