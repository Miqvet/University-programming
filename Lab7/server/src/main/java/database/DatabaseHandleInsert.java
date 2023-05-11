package database;

import database.enumsOfSQL.Inserts;
import mainCollection.collection.*;
import transmitted.MarinBuffer;
import transmitted.User;

import java.sql.*;
import java.time.LocalDateTime;


public class DatabaseHandleInsert {

    public static SpaceMarine insertMarine(DatabaseHandleConnect databaseHandleConnect,MarinBuffer marinBuffer, User user) throws SQLException {
        SpaceMarine marine;
        databaseHandleConnect.setOffCommitMode();
        Savepoint savepoint = databaseHandleConnect.setSavepoint();
        try (PreparedStatement preparedInsertChapterStatement =
                     databaseHandleConnect.getPreparedStatement(Inserts.INSERT_CHAPTER.getTitle(), true);
             PreparedStatement preparedInsertCoordinatesStatement =
                     databaseHandleConnect.getPreparedStatement(Inserts.INSERT_COORDINATES.getTitle(), true);
             PreparedStatement preparedInsertMarineStatement =
                     databaseHandleConnect.getPreparedStatement(Inserts.INSERT_MARINE.getTitle(), true)){

            LocalDateTime creationTime = LocalDateTime.now();
            preparedInsertChapterStatement.setString(1, marinBuffer.chapter().getName());
            preparedInsertChapterStatement.setString(2, marinBuffer.chapter().getWorld());
            if (preparedInsertChapterStatement.executeUpdate() == 0) throw new SQLException();
            ResultSet generatedChapterKeys = preparedInsertChapterStatement.getGeneratedKeys();
            long chapterId;
            if (generatedChapterKeys.next()) {
                chapterId = generatedChapterKeys.getLong(1);
            } else {
                throw new SQLException();
            }
            preparedInsertCoordinatesStatement.setLong(1, marinBuffer.coordinates().getX());
            preparedInsertCoordinatesStatement.setLong(2, marinBuffer.coordinates().getY());
            if (preparedInsertCoordinatesStatement.executeUpdate() == 0) throw new SQLException();
            ResultSet generatedCoordinatesKeys = preparedInsertCoordinatesStatement.getGeneratedKeys();
            long coordinatesId;
            if (generatedCoordinatesKeys.next()) {
                coordinatesId = generatedCoordinatesKeys.getLong(1);
            } else {
                throw new SQLException();
            }
            preparedInsertMarineStatement.setString(1, user.getLogin());
            preparedInsertMarineStatement.setString(2, marinBuffer.name());
            preparedInsertMarineStatement.setLong(3, marinBuffer.health());
            preparedInsertMarineStatement.setLong(4, coordinatesId);
            preparedInsertMarineStatement.setString(5, marinBuffer.category().toString());
            preparedInsertMarineStatement.setString(6, marinBuffer.weaponType().toString());
            preparedInsertMarineStatement.setString(7, marinBuffer.meleeWeapon().toString());
            preparedInsertMarineStatement.setLong(8, chapterId);
            preparedInsertMarineStatement.setTimestamp(9, Timestamp.valueOf(creationTime));
            if (preparedInsertMarineStatement.executeUpdate() == 0) throw new SQLException();
            ResultSet generatedMarineKeys = preparedInsertMarineStatement.getGeneratedKeys();
            long spaceMarineId;
            if (generatedMarineKeys.next()) {
                spaceMarineId = generatedMarineKeys.getLong(2);
            } else {
                throw new SQLException();
            }
            databaseHandleConnect.commit();
            return new SpaceMarine(marinBuffer.getAll(), spaceMarineId,user.getLogin());
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении группы запросов на обновление объекта!");
            databaseHandleConnect.rollback(savepoint);
            throw new SQLException();
        }finally {
            databaseHandleConnect.setOnCommitMode();
        }
    }

}
