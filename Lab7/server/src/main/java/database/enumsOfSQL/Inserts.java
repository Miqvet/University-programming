package database.enumsOfSQL;

import static database.enumsOfSQL.DatabaseInfo.*;

public enum Inserts {
    INSERT_MARINE("INSERT INTO " +
            MARINE_TABLE.getTitle() + " (" +
            MARINE_TABLE_USER_ID_COLUMN.getTitle() + ", " +
            MARINE_TABLE_NAME_COLUMN.getTitle() + ", " +
            MARINE_TABLE_HEALTH_COLUMN.getTitle() + ", " +
            MARINE_TABLE_COORDINATES_COLUMN.getTitle() + ", " +
            MARINE_TABLE_ASTRAL_CATEGORY_COLUMN.getTitle() + ", " +
            MARINE_TABLE_WEAPON_TYPE_COLUMN.getTitle() + ", " +
            MARINE_TABLE_MELEE_WEAPON_COLUMN.getTitle() + ", " +
            MARINE_TABLE_CHAPTER_ID_COLUMN.getTitle() + ", " +
            MARINE_TABLE_CREATION_DATE_COLUMN.getTitle() +
            ") VALUES (?, ?, ?, ?, ?,?,?, ?, ?)"),
    INSERT_COORDINATES("INSERT INTO " +
            COORDINATES_TABLE.getTitle() + " (" +
            COORDINATES_TABLE_X_COLUMN.getTitle() + ", " +
            COORDINATES_TABLE_Y_COLUMN.getTitle() +
            ") VALUES (?, ?)"),
    INSERT_CHAPTER("INSERT INTO " +
            CHAPTER_TABLE.getTitle() + " (" +
            CHAPTER_TABLE_NAME_COLUMN.getTitle() + ", " +
            CHAPTER_TABLE_WORLD_COLUMN.getTitle() +
            ") VALUES (?, ?)"),
    INSERT_USER("INSERT INTO " +
            USER_TABLE.getTitle() + " (" +
            USER_TABLE_LOGIN_COLUMN.getTitle() + ", " +
            USER_TABLE_PASSWORD_COLUMN.getTitle() + ") VALUES (?, ?)");
    private String title;

    Inserts(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
