package database;

import database.enumsOfSQL.DatabaseInfo;
import database.enumsOfSQL.Selects;
import mainCollection.collection.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class DatabaseHandleGetter {
    public static long getCoordinatesIdByMarineId(DatabaseHandleConnect databaseHandleConnect,long marineId) throws SQLException {
        long coordinatesId;
        try(PreparedStatement preparedSelectMarineByIdStatement = databaseHandleConnect.getPreparedStatement(Selects.SELECT_COORDINATES_ID_BY_MARINE_ID.getTitle(), false);) {
            preparedSelectMarineByIdStatement.setLong(1, marineId);
            ResultSet resultSet = preparedSelectMarineByIdStatement.executeQuery();
            System.out.println("Выполнен запрос SELECT_COORDINATES_ID_BY_MARINE_ID.");
            if (resultSet.next()) {
                coordinatesId = resultSet.getLong(DatabaseInfo.MARINE_TABLE_COORDINATES_COLUMN.getTitle());
            } else throw new SQLException();
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_COORDINATES_ID_BY_MARINE_ID!");
            throw new SQLException(exception);
        }
        return coordinatesId;
    }

    public static long getChapterIdByMarineId(DatabaseHandleConnect databaseHandleConnect,long marineId) throws SQLException {
        long chapterId;
        try (PreparedStatement preparedSelectMarineByIdStatement =
                     databaseHandleConnect.getPreparedStatement(Selects.SELECT_CHAPTER_ID_BY_MARINE_ID.getTitle(), false);){
            preparedSelectMarineByIdStatement.setLong(1, marineId);
            ResultSet resultSet = preparedSelectMarineByIdStatement.executeQuery();
            System.out.println("Выполнен запрос SELECT_CHAPTER_ID_BY_MARINE_ID.");
            if (resultSet.next()) {
                chapterId = resultSet.getLong(DatabaseInfo.MARINE_TABLE_CHAPTER_ID_COLUMN.getTitle());
            } else throw new SQLException();
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при выполнении запроса SELECT_CHAPTER_ID_BY_MARINE_ID!");
            throw new SQLException(exception);
        }
        return chapterId;
    }
    public static ArrayDeque<SpaceMarine> getCollection(DatabaseHandleConnect databaseHandleConnect) throws SQLException {
        ArrayDeque<SpaceMarine> marineList = new ArrayDeque<>();
        PreparedStatement preparedSelectAllStatement = null;
        preparedSelectAllStatement = databaseHandleConnect.getPreparedStatement(Selects.SELECT_ALL_MARINES.getTitle(), true);
        ResultSet resultSet = preparedSelectAllStatement.executeQuery();
        while (resultSet.next()) {
            marineList.add(createMarine(databaseHandleConnect,resultSet));
        }
        return marineList;
    }
    public static Chapter getChapter(DatabaseHandleConnect databaseHandleConnect,long chapterId) throws SQLException {
        Chapter chapter;
        try(PreparedStatement preparedSelectChapterByIdStatement =
                    databaseHandleConnect.getPreparedStatement(Selects.SELECT_CHAPTER_BY_ID.getTitle(), true);) {
            preparedSelectChapterByIdStatement.setLong(1, chapterId);
            ResultSet resultSet = preparedSelectChapterByIdStatement.executeQuery();
            if (resultSet.next()) {
                chapter = new Chapter(
                        resultSet.getString(DatabaseInfo.CHAPTER_TABLE_NAME_COLUMN.getTitle()),
                        resultSet.getString(DatabaseInfo.CHAPTER_TABLE_WORLD_COLUMN.getTitle())
                );
            } else throw new SQLException();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
        return chapter;
    }
    public static Coordinates getCoordinates(DatabaseHandleConnect databaseHandleConnect,long coordinatesId) throws SQLException {
        Coordinates coordinates;
        try(PreparedStatement  preparedSelectCoordinatesByIdStatement =
                    databaseHandleConnect.getPreparedStatement(Selects.SELECT_COORDINATES_BY_ID.getTitle(), false);) {
            preparedSelectCoordinatesByIdStatement.setLong(1, coordinatesId);
            ResultSet resultSet = preparedSelectCoordinatesByIdStatement.executeQuery();
            if (resultSet.next()) {
                coordinates = new Coordinates(
                        resultSet.getLong(DatabaseInfo.COORDINATES_TABLE_X_COLUMN.getTitle()),
                        resultSet.getInt(DatabaseInfo.COORDINATES_TABLE_Y_COLUMN.getTitle())
                );
            } else throw new SQLException();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
        return coordinates;
    }
    public static SpaceMarine createMarine(DatabaseHandleConnect databaseHandleConnect,ResultSet resultSet) throws SQLException {
        ArrayList<Object> buffer = new ArrayList<>();
        long id = resultSet.getLong(DatabaseInfo.MARINE_TABLE_ID_COLUMN.getTitle());
        String name = resultSet.getString(DatabaseInfo.MARINE_TABLE_NAME_COLUMN.getTitle());
        LocalDateTime creationDate = resultSet.getTimestamp(DatabaseInfo.MARINE_TABLE_CREATION_DATE_COLUMN.getTitle()).toLocalDateTime();
        Long health = resultSet.getLong(DatabaseInfo.MARINE_TABLE_HEALTH_COLUMN.getTitle());
        AstartesCategory category = AstartesCategory.valueOf(resultSet.getString(DatabaseInfo.MARINE_TABLE_ASTRAL_CATEGORY_COLUMN.getTitle()));
        Weapon weaponType = Weapon.valueOf(resultSet.getString(DatabaseInfo.MARINE_TABLE_WEAPON_TYPE_COLUMN.getTitle()));
        MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(resultSet.getString(DatabaseInfo.MARINE_TABLE_MELEE_WEAPON_COLUMN.getTitle()));
        Coordinates coordinates = getCoordinates(databaseHandleConnect, getCoordinatesIdByMarineId(databaseHandleConnect,id));
        Chapter chapter = getChapter(databaseHandleConnect, getChapterIdByMarineId(databaseHandleConnect,id));
        String owner = resultSet.getString(DatabaseInfo.MARINE_TABLE_USER_ID_COLUMN.getTitle());
        buffer.add(name);
        buffer.add(health);
        buffer.add(coordinates.getX());
        buffer.add(coordinates.getY());
        buffer.add(category);
        buffer.add(weaponType);
        buffer.add(meleeWeapon);
        buffer.add(chapter.getName());
        buffer.add(chapter.getWorld());
        buffer.add(creationDate);
        SpaceMarine asd = new SpaceMarine(buffer, id,owner);
        return asd;
    }
}
