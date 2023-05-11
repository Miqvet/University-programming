package database;

import database.enumsOfSQL.Deletes;
import mainCollection.collection.SpaceMarine;

import java.sql.*;

public class DatabaseHandleRemove {
    public static void removeMarine(DatabaseHandleConnect databaseHandleConnect, long idMarine) throws SQLException {
        databaseHandleConnect.setOffCommitMode();
        Savepoint savepoint = databaseHandleConnect.setSavepoint();

        try (PreparedStatement preparedRemoveCoordinatesStatement = databaseHandleConnect.getPreparedStatement(
                Deletes.DELETE_COORDINATES_BY_ID.getTitle(), false);
             PreparedStatement preparedRemoveMarineStatement = databaseHandleConnect.getPreparedStatement(
                     Deletes.DELETE_MARINE_BY_ID.getTitle(), false);
             PreparedStatement preparedRemoveChapterStatement = databaseHandleConnect.getPreparedStatement(
                     Deletes.DELETE_CHAPTER_BY_ID.getTitle(), false)) {

            long idCoordinates = DatabaseHandleGetter.getCoordinatesIdByMarineId(databaseHandleConnect, idMarine);
            long idChapter = DatabaseHandleGetter.getChapterIdByMarineId(databaseHandleConnect, idMarine);

            preparedRemoveMarineStatement.setLong(1, idMarine);
            preparedRemoveMarineStatement.executeUpdate();

            preparedRemoveCoordinatesStatement.setLong(1, idCoordinates);
            preparedRemoveCoordinatesStatement.executeUpdate();

            preparedRemoveChapterStatement.setLong(1, idChapter);
            preparedRemoveChapterStatement.executeUpdate();

            databaseHandleConnect.commit();
        }catch (SQLException e){
            databaseHandleConnect.rollback(savepoint);
            throw e;
        }finally {
            databaseHandleConnect.setOnCommitMode();
        }
    }
}
