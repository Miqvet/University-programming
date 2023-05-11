package database;

import database.enumsOfSQL.Inserts;
import database.enumsOfSQL.Selects;
import transmitted.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static utilities.HashCreator.hashingToSHA256;


public class DatabaseUser {
    public static boolean checkUserByUsernameAndPassword(User user,DatabaseHandleConnect databaseHandleConnect) throws SQLException{
        PreparedStatement statement = null;
        if(!checkUserByLogin(user, databaseHandleConnect)){
            throw new RuntimeException("Данного Login не существует \n");
        }
        statement =databaseHandleConnect.getPreparedStatement(Selects.SELECT_USER_BY_LOGIN_AND_PASSWORD.getTitle(), false);
        statement.setString(1, user.getLogin());
        statement.setString(2, hashingToSHA256(user.getPassword()));
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }
    public static boolean checkUserByLogin(User user,DatabaseHandleConnect databaseHandleConnect) throws SQLException{
        PreparedStatement statement = null;
        statement = databaseHandleConnect.getPreparedStatement(Selects.SELECT_USER_BY_LOGIN.getTitle(), false);
        statement.setString(1, user.getLogin());
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();

    }

    public static boolean insertUser(User user,DatabaseHandleConnect databaseHandleConnect) throws SQLException {
        PreparedStatement statement = null;
        if (checkUserByLogin(user, databaseHandleConnect)) return false;
        statement = databaseHandleConnect.getPreparedStatement(Inserts.INSERT_USER.getTitle(), false);
        statement.setString(1, user.getLogin());
        statement.setString(2, hashingToSHA256(user.getPassword()));
        if (statement.executeUpdate() == 0) throw new SQLException();
        return true;
    }
}
