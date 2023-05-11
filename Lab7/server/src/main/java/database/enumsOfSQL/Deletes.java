package database.enumsOfSQL;

public enum Deletes {
    DELETE_MARINE_BY_ID("DELETE FROM " + DatabaseInfo.MARINE_TABLE.getTitle() +
            " WHERE " + DatabaseInfo.MARINE_TABLE_ID_COLUMN.getTitle() + " = ?"),
    DELETE_CHAPTER_BY_ID("DELETE FROM " + DatabaseInfo.CHAPTER_TABLE.getTitle() +
            " WHERE " + DatabaseInfo.CHAPTER_TABLE_ID_COLUMN.getTitle() + " = ?"),
    DELETE_COORDINATES_BY_ID("DELETE FROM " + DatabaseInfo.COORDINATES_TABLE.getTitle() +
            " WHERE " + DatabaseInfo.COORDINATES_TABLE_ID_COLUMN.getTitle() + " = ?");
    private String title;

    Deletes(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
