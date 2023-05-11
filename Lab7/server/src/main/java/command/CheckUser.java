package command;

import database.DatabaseUser;
import serverSystemClasses.CollectionManager;
import transmitted.MarinBuffer;
import transmitted.Response;
import transmitted.ServerStatus;
import transmitted.User;

import java.sql.SQLException;

public class CheckUser extends AncestorCommand{
    private static String description = "";

    @Override
    public Response execute(String argument, MarinBuffer objArgument, CollectionManager collectionManager, User user) {
        if (argument.equals("") && objArgument == null){
            try {
                if (DatabaseUser.checkUserByUsernameAndPassword(user,collectionManager.getDatabaseHandleConnect())){
                    return new Response(ServerStatus.OK,"Пользователь подтверждён.\n");
                }
                return new Response(ServerStatus.REQUEST_ERROR,"Пользователь не был подтверждён, пароль и Login не совпали \n");
            } catch (SQLException e) {
                return new Response(ServerStatus.ERROR,"Пользователь не был добавлен. Произошла ошибка в время добавления. \n");
            }catch (RuntimeException e) {
                return new Response(ServerStatus.ERROR,e.getMessage());
            }
        }
        return new Response(ServerStatus.REQUEST_ERROR,"Пользователь не был подтверждён, т.к. введены не верные данные.\n");
    }

    @Override
    public String getDescription() {
        return null;
    }
}
