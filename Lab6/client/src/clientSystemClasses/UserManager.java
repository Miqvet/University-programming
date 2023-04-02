package clientSystemClasses;

import exceptions.IncorrectInputInScriptException;
import exceptions.ScriptRecursionException;
import transmitted.MarinBuffer;
import transmitted.Request;
import transmitted.Response;
import transmitted.ServerStatus;
import utilities.Output;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static clientSystemClasses.CreateNewBufferMarine.generateMarineAdd;

public class UserManager {
    private final static HashSet<String> COMMAND_WITH_ARGUMENT_OBJECT=
            new HashSet<>(Arrays.asList("update"));
    private final HashSet<String> COMMAND_WITH_OBJECT =
            new HashSet<>(Arrays.asList("add_if_max", "add", "add_if_min", "remove_greater"));
    private final HashSet<String> COMMAND_WITH_ARGUMENT =
            new HashSet<>(Arrays.asList("remove_by_id", "execute_script",
                    "count_by_health", "filter_less_than_category"));
    private final HashSet<String> COMMAND_WITHOUT =
            new HashSet<>(Arrays.asList("print_field_descending_health",
                    "clear", "show", "info", "help","exit"));
    private Scanner scanner;
    private Stack<Scanner> stackOfScanner = new Stack<>() ;
    private Stack<File> stackFile = new Stack<>();
    public UserManager(Scanner scanner){
        this.scanner = scanner;
    }
    public Request createRequest(ServerStatus serverStatus){
        String strInput = null;
        String[] inputData;
        Status inputStatus = null;
        Request request = null;
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
                    strInput = scanner.nextLine().trim();
                    inputStatus = handleInput(strInput);
                }
                catch (NoSuchElementException e){
                    Output.println();
                    Output.printerror("Произошла ошибка при вводе команды!");
                    Output.println("Пожалуйста хватит");
                    System.exit(0);
                }
                catch (IllegalStateException e){
                    Output.println();
                    Output.printerror("Произошла ошибка при вводе команды!");
                }
            }while(inputStatus == Status.ERROR);
            request = createRequest(strInput);
            if (request.isEmpty()){
                throw new IncorrectInputInScriptException();
            }
        }catch (IncorrectInputInScriptException exception) {
            Output.printerror("Выполнение скрипта прервано!");
            while (!stackOfScanner.isEmpty()) {
                scanner.close();
                scanner =stackOfScanner.pop();
            }
            stackFile.clear();
            System.out.println("Ошибка скрипта");
            return new Request(null,null,null);
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
        String[] commandsAndArguments = strInput.split(" ");
        try {
            if (commandsAndArguments[0] == "execute_script"){
                File scriptFile = new File(commandsAndArguments[1]);
                if (!scriptFile.exists()) throw new FileNotFoundException();
                if (!stackOfScanner.isEmpty() && stackOfScanner.search(scriptFile) != -1)
                    throw new ScriptRecursionException("Всё плохо скрипт зациклился");
                stackOfScanner.push(scanner);
                stackFile.push(scriptFile);
                scanner = new Scanner(scriptFile);
                Output.println("Выполняю скрипт '" + scriptFile.getName() + "'...");
            }
            else if  (COMMAND_WITH_ARGUMENT_OBJECT.contains(commandsAndArguments[0])){
                MarinBuffer marine = generateMarineAdd(scanner,!(stackOfScanner.isEmpty()));
                return new Request(commandsAndArguments[0], commandsAndArguments[1], marine);
            }
            else if(COMMAND_WITH_ARGUMENT.contains(commandsAndArguments[0])){
                return new Request(commandsAndArguments[0], commandsAndArguments[1], null);
            }
            else if(COMMAND_WITH_OBJECT.contains(commandsAndArguments[0])){
                MarinBuffer marine = generateMarineAdd(scanner,!(stackOfScanner.isEmpty()));
                return new Request(commandsAndArguments[0], null, marine);
            }
            else{
                return new Request(commandsAndArguments[0], null,null);
            }
        } catch (FileNotFoundException exception) {
            Output.printerror("Файл со скриптом не найден!");
        } catch (ScriptRecursionException exception) {
            Output.printerror("Скрипты не могут вызываться рекурсивно!");
            throw new IncorrectInputInScriptException();
        }catch (IncorrectInputInScriptException exception) {
            Output.printerror("Скрипты не могут вызываться рекурсивно!");
            throw new IncorrectInputInScriptException();
        }
        return null;
    }
}
