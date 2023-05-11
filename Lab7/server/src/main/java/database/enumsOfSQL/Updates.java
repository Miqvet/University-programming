package database.enumsOfSQL;

public enum Updates {
    UPDATE_COORDINATES_BY_ID("UPDATE " + DatabaseInfo.COORDINATES_TABLE.getTitle()+ " SET " +
            DatabaseInfo.COORDINATES_TABLE_X_COLUMN.getTitle() + " = ?, " +
            DatabaseInfo.COORDINATES_TABLE_Y_COLUMN.getTitle() + " = ?" + " WHERE " +
            DatabaseInfo.COORDINATES_TABLE_ID_COLUMN.getTitle() + " = ?"),
    UPDATE_CHAPTER_BY_ID("UPDATE " + DatabaseInfo.CHAPTER_TABLE.getTitle() + " SET " +
            DatabaseInfo.CHAPTER_TABLE_NAME_COLUMN.getTitle() + " = ?, " +
            DatabaseInfo.CHAPTER_TABLE_WORLD_COLUMN.getTitle() + " = ?" + " WHERE " +
            DatabaseInfo.CHAPTER_TABLE_ID_COLUMN.getTitle() + " = ?"),
    UPDATE_MARINE_BY_ID("UPDATE " + DatabaseInfo.MARINE_TABLE.getTitle() + " SET " +
            DatabaseInfo.MARINE_TABLE_NAME_COLUMN.getTitle() + " = ?, " +
            DatabaseInfo.MARINE_TABLE_HEALTH_COLUMN.getTitle() + " = ?, " +
            DatabaseInfo.MARINE_TABLE_ASTRAL_CATEGORY_COLUMN.getTitle() + " = ?, " +
            DatabaseInfo.MARINE_TABLE_WEAPON_TYPE_COLUMN.getTitle() + " = ?, " +
            DatabaseInfo.MARINE_TABLE_MELEE_WEAPON_COLUMN.getTitle() + " = ?"
            + " WHERE " + DatabaseInfo.MARINE_TABLE_ID_COLUMN.getTitle() + " = ?");
    private String title;

    Updates(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
