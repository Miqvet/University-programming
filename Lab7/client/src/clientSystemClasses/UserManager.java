package clientSystemClasses;

import exceptions.IncorrectInputInScriptException;
import exceptions.ScriptRecursionException;
import transmitted.MarinBuffer;
import transmitted.Request;
import transmitted.ServerStatus;
import transmitted.User;
import utilities.Output;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static clientSystemClasses.CreateBufferMarine.generateMarineAdd;

public class UserManager {
    private User user;
    private final static HashSet<? extends String> COMMAND_WITH_ARGUMENT_OBJECT=
            new HashSet<>(List.of("update"));
    private final static HashSet<? extends String> SCRIPT_COMMAND=
            new HashSet<>(List.of("execute_script"));
    private final HashSet<? extends String> COMMAND_WITH_OBJECT =
            new HashSet<>(Arrays.asList("add_if_max", "add", "add_if_min", "remove_greater"));
    private final HashSet<? extends String> COMMAND_WITH_ARGUMENT =
            new HashSet<>(Arrays.asList("remove_by_id", "execute_script",
                    "count_by_health", "filter_less_than_category"));
    private final HashSet<? extends String> COMMAND_WITHOUT =
            new HashSet<>(Arrays.asList("print_field_descending_health",
                    "clear", "show", "info", "help","exit"));
    private Scanner scanner;
    private final Stack<Scanner> stackOfScanner = new Stack<>() ;
    private final Stack<File> stackFile = new Stack<>();
    public UserManager(Scanner scanner){
        this.scanner = scanner;
    }
    public void setUser(User user){
        this.user = user;
    }
    public Request createRequest(ServerStatus serverStatus){
        String strInput = null;
        Status inputStatus = null;
        Request request;
        try{
            do{
                try{
                    if ((!stackOfScanner.isEmpty()) & serverStatus == ServerStatus.ERROR){
                        throw new IncorrectInputInScriptException();
                    }
                    while ((!stackOfScanner.isEmpty()) && (!scanner.hasNextLine())) {
                        scanner.close();
                        scanner = stackOfScanner.pop();
                    }
                    if (stackOfScanner.isEmpty()) Output.println("\nВведите команду");
                    strInput = scanner.nextLine().trim().toLowerCase();
                    inputStatus = handleInput(strInput);
                }
                catch (NoSuchElementException e){
                    Output.println();
                    Output.printerror("Произошла критическая ошибка при вводе команды!\n" +
                            "Была введена комбинация ctrl + D или что-то подобное");
                    return new Request("exit", null,null,user);
                    //Принудительное завершение работы
                }
                catch (IllegalStateException e){
                    Output.println();
                    Output.printerror("Произошла ошибка при вводе команды!");
                }
                if(inputStatus == Status.ERROR) Output.printerror("Некорректный формат ввода!\n" +
                        "Используйте команду help для получения справки");
            }while(inputStatus == Status.ERROR);
            request = createRequest(strInput);
            if (request == null){
                throw new IncorrectInputInScriptException();
            }
        }catch (IncorrectInputInScriptException exception) {
            Output.printerror("Выполнение скрипта прервано!");
            while (!stackOfScanner.isEmpty()) {
                scanner.close();
                scanner = stackOfScanner.pop();
            }
            scanner = new Scanner(System.in);
            stackFile.clear();
            System.out.println("Ошибка скрипта");
            return new Request(null,null,null,user);
        }
        return request;
    }
    private Status handleInput(String strInput){
        String[] commandsAndArguments = strInput.split(" ");
        if (COMMAND_WITH_ARGUMENT_OBJECT.contains(commandsAndArguments[0]) | COMMAND_WITH_ARGUMENT.contains(commandsAndArguments[0])){
            if(commandsAndArguments.length == 2) return Status.OK;
        }
        else if (COMMAND_WITHOUT.contains(commandsAndArguments[0]) | COMMAND_WITH_OBJECT.contains(commandsAndArguments[0])){
            if(commandsAndArguments.length == 1) return Status.OK;
        }
        else{
            return Status.ERROR;
        }
        return Status.ERROR;
    }

    private Request createRequest(String strInput) throws IncorrectInputInScriptException {
        try {
            return handleCommand(strInput);
        } catch (FileNotFoundException exception) {
            Output.printerror("Файл со скриптом не найден!");
        } catch (ScriptRecursionException exception) {
            Output.printerror("Скрипты не могут вызываться рекурсивно!");
            throw new IncorrectInputInScriptException();
        }catch (IncorrectInputInScriptException exception) {
            Output.printerror("Ошибка в скрипте!");
            throw new IncorrectInputInScriptException();
        }
        return null;
    }

    private Request handleCommand(String strInput) throws FileNotFoundException, ScriptRecursionException, IncorrectInputInScriptException {
        String[] commandsAndArguments = strInput.split(" ");
        try{
            if (SCRIPT_COMMAND.contains(commandsAndArguments[0])){
                Output.println("Начинаю поиск и обработку файла скрипта");
                File scriptFile = new File(commandsAndArguments[1]);
                if (!scriptFile.exists()) throw new FileNotFoundException();
                if (!stackOfScanner.isEmpty() && stackFile.search(scriptFile) != -1)
                    throw new ScriptRecursionException("Всё плохо скрипт зациклился");
                stackOfScanner.push(scanner);
                stackFile.push(scriptFile);
                scanner = new Scanner(scriptFile);
                Output.println("Выполняю скрипт " + scriptFile.getName() + "...");
                return new Request(null,null,null,user);
            }
            else if  (COMMAND_WITH_ARGUMENT_OBJECT.contains(commandsAndArguments[0])){
                MarinBuffer marine = generateMarineAdd(scanner,!(stackOfScanner.isEmpty()));
                return new Request(commandsAndArguments[0], commandsAndArguments[1], marine,user);
            }
            else if(COMMAND_WITH_ARGUMENT.contains(commandsAndArguments[0])){
                return new Request(commandsAndArguments[0], commandsAndArguments[1], null,user);
            }
            else if(COMMAND_WITH_OBJECT.contains(commandsAndArguments[0])){
                MarinBuffer marine = generateMarineAdd(scanner,!(stackOfScanner.isEmpty()));
                return new Request(commandsAndArguments[0], null, marine,user);
            }
            else{
                return new Request(commandsAndArguments[0], null,null,user);
            }
        } catch (NoSuchElementException e){
            Output.printerror("НЕ НАДО ЛОМАТЬ ПРОГУ! Клиент завершает свою работу.");
            return new Request("exit", null, null,user);
        }
    }
}


