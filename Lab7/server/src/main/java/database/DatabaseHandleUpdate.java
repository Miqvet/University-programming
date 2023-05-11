package database;

import database.enumsOfSQL.DatabaseInfo;
import database.enumsOfSQL.Selects;
import database.enumsOfSQL.Updates;
import transmitted.MarinBuffer;
import transmitted.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.time.LocalDateTime;

public class DatabaseHandleUpdate {
    public static void updateMarine(DatabaseHandleConnect databaseHandleConnect,
                                    MarinBuffer marinBuffer, User user, long marineId) throws SQLException {
        databaseHandleConnect.setOffCommitMode();
        Savepoint savepoint = databaseHandleConnect.setSavepoint();
        try(PreparedStatement preparedUpdateMarineByIdStatement =
                    databaseHandleConnect.getPreparedStatement(Updates.UPDATE_MARINE_BY_ID.getTitle(), false);
            PreparedStatement  preparedUpdateCoordinatesByMarineIdStatement =
                    databaseHandleConnect.getPreparedStatement(Updates.UPDATE_COORDINATES_BY_ID.getTitle(), false);
            PreparedStatement preparedUpdateChapterByIdStatement =
                    databaseHandleConnect.getPreparedStatement(Updates.UPDATE_CHAPTER_BY_ID.getTitle(), true)){

            preparedUpdateCoordinatesByMarineIdStatement.setLong(1,
                    marinBuffer.coordinates().getX());
            preparedUpdateCoordinatesByMarineIdStatement.setLong(2,
                    marinBuffer.coordinates().getY());
            preparedUpdateCoordinatesByMarineIdStatement.setLong(3,
                    DatabaseHandleGetter.getCoordinatesIdByMarineId(databaseHandleConnect,marineId));
            if (preparedUpdateCoordinatesByMarineIdStatement.executeUpdate() == 0) throw new SQLException();

            preparedUpdateChapterByIdStatement.setString(1,marinBuffer.chapter().getName());
            preparedUpdateChapterByIdStatement.setString(2,marinBuffer.chapter().getWorld());
            preparedUpdateChapterByIdStatement.setLong(3,DatabaseHandleGetter.
                    getChapterIdByMarineId(databaseHandleConnect,marineId));
            if (preparedUpdateChapterByIdStatement.executeUpdate() == 0) throw new SQLException();

            preparedUpdateMarineByIdStatement.setString(1,marinBuffer.name());
            preparedUpdateMarineByIdStatement.setLong(2,marinBuffer.health());
            preparedUpdateMarineByIdStatement.setString(3,marinBuffer.category().toString());
            preparedUpdateMarineByIdStatement.setString(4,marinBuffer.weaponType().toString());
            preparedUpdateMarineByIdStatement.setString(5,marinBuffer.meleeWeapon().toString());
            preparedUpdateMarineByIdStatement.setLong(6,marineId);
            if (preparedUpdateMarineByIdStatement.executeUpdate() == 0) throw new SQLException();
            PreparedStatement preparedSelectUserByIdStatement = databaseHandleConnect.getPreparedStatement(
                    Selects.SELECT_LOGIN_USER_BY_MARINE_ID.getTitle(), false);
            preparedSelectUserByIdStatement.setLong(1, marineId);
            ResultSet resultSet = preparedSelectUserByIdStatement.executeQuery();
            String login;
            if (resultSet.next()) {
                login = resultSet.getString(DatabaseInfo.MARINE_TABLE_USER_ID_COLUMN.getTitle());
            } else throw new SQLException();
            if(!login.equals(user.getLogin())){
                throw new SQLException();
            }
            databaseHandleConnect.commit();
        } catch (SQLException e){
            databaseHandleConnect.rollback(savepoint);
        }finally {
            databaseHandleConnect.setOnCommitMode();
        }
    }
}
