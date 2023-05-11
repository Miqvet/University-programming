package database.enumsOfSQL;

public enum Selects {
    SELECT_ALL_MARINES("SELECT * FROM " + DatabaseInfo.MARINE_TABLE.getTitle()),
    SELECT_MARINE_BY_ID(SELECT_ALL_MARINES.getTitle() + " WHERE " +
            DatabaseInfo.MARINE_TABLE_ID_COLUMN.getTitle() + " = ?"),
    SELECT_COORDINATES_ID_BY_MARINE_ID("SELECT " + DatabaseInfo.MARINE_TABLE_COORDINATES_COLUMN.getTitle() +
            " FROM " + DatabaseInfo.MARINE_TABLE.getTitle() + " WHERE " +
            DatabaseInfo.MARINE_TABLE_ID_COLUMN.getTitle() + " = ?"),
    SELECT_CHAPTER_ID_BY_MARINE_ID("SELECT " + DatabaseInfo.MARINE_TABLE_CHAPTER_ID_COLUMN.getTitle() +
            " FROM " + DatabaseInfo.MARINE_TABLE.getTitle() + " WHERE " +
            DatabaseInfo.MARINE_TABLE_ID_COLUMN.getTitle() + " = ?"),
    SELECT_MARINE_BY_USER_ID(SELECT_MARINE_BY_ID.getTitle() + " AND " +
            DatabaseInfo.MARINE_TABLE_USER_ID_COLUMN.getTitle() + " = ?"),
    SELECT_LOGIN_USER_BY_MARINE_ID("SELECT " + DatabaseInfo.MARINE_TABLE_USER_ID_COLUMN.getTitle() +
            " FROM " + DatabaseInfo.MARINE_TABLE.getTitle() + " WHERE " +
            DatabaseInfo.MARINE_TABLE_ID_COLUMN.getTitle() + " = ?"),
    SELECT_CHAPTER_BY_ID("SELECT * FROM " + DatabaseInfo.CHAPTER_TABLE.getTitle() +
            " WHERE " + DatabaseInfo.CHAPTER_TABLE_ID_COLUMN.getTitle() + " = ?"),
    SELECT_COORDINATES_BY_ID("SELECT * FROM " + DatabaseInfo.COORDINATES_TABLE.getTitle() +
            " WHERE " + DatabaseInfo.COORDINATES_TABLE_ID_COLUMN.getTitle() + " = ?"),
    SELECT_USER_BY_LOGIN("SELECT * FROM " + DatabaseInfo.USER_TABLE.getTitle() +
            " WHERE " + DatabaseInfo.USER_TABLE_LOGIN_COLUMN.getTitle() + " = ?;"),
    SELECT_USER_BY_LOGIN_AND_PASSWORD("SELECT * FROM " + DatabaseInfo.USER_TABLE.getTitle() +
            " WHERE " + DatabaseInfo.USER_TABLE_LOGIN_COLUMN.getTitle() + " = ? " +
            "AND " + DatabaseInfo.USER_TABLE_PASSWORD_COLUMN.getTitle() + " = ?");
    private String title;

    Selects(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}