package command;

import database.DatabaseHandleInsert;
import database.DatabaseUser;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;
import transmitted.User;

import java.sql.SQLException;

public class RegisterUser extends AncestorCommand{
    private static String description = "";

    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager, User user) {
        if (argument.equals("") && objArgument == null){
            try {
                if (DatabaseUser.insertUser(user,collectionManager.getDatabaseHandleConnect())){
                    return new Response(ServerStatus.OK,"Пользователь был добавлен.\n");
                }
                return new Response(ServerStatus.REQUEST_ERROR,"Пользователь не был добавлен. Данный Login уже зарезервирован. \n");
            } catch (SQLException e) {
                return new Response(ServerStatus.ERROR,"Пользователь не был добавлен. Произошла ошибка в время добавления." +
                        "Попробуйте позднее \n");
            }
        }
        return new Response(ServerStatus.REQUEST_ERROR,"Пользователь не был добавлен, т.к. введены не верные данные.\n");
    }

    @Override
    public String getDescription() {
        return null;
    }
}
