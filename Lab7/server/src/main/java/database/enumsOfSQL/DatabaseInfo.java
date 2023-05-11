package database.enumsOfSQL;

public enum DatabaseInfo {
     MARINE_TABLE("spacemarine"),
     USER_TABLE ("my_user"),
     COORDINATES_TABLE("coordinates"),
     CHAPTER_TABLE("chapter"),
     CATEGORY_TABLE("marine_category"),
     MELEE_TABLE("melee_weapon"),
     WEAPON_TABLE("weapon"),
    // MARINE_TABLE column names
     MARINE_TABLE_USER_ID_COLUMN("user_id"),
     MARINE_TABLE_ID_COLUMN("ship_id"),
     MARINE_TABLE_NAME_COLUMN("name"),
     MARINE_TABLE_HEALTH_COLUMN("health"),
     MARINE_TABLE_COORDINATES_COLUMN("coordinates"),
     MARINE_TABLE_ASTRAL_CATEGORY_COLUMN("astral_category"),
     MARINE_TABLE_WEAPON_TYPE_COLUMN("weapon_type"),
     MARINE_TABLE_MELEE_WEAPON_COLUMN("melee_weapon"),
     MARINE_TABLE_CHAPTER_ID_COLUMN("chapter"),
     MARINE_TABLE_CREATION_DATE_COLUMN("creation_date"),
    // USER_TABLE column names
     USER_TABLE_ID_COLUMN("user_id"),
     USER_TABLE_LOGIN_COLUMN("login"),
     USER_TABLE_PASSWORD_COLUMN("password"),
    // COORDINATES_TABLE column names
     COORDINATES_TABLE_ID_COLUMN("coordinate_id"),
     COORDINATES_TABLE_X_COLUMN("x"),
     COORDINATES_TABLE_Y_COLUMN("y"),
    // CHAPTER_TABLE column CHAPTER
     CHAPTER_TABLE_ID_COLUMN("chapter_id"),
     CHAPTER_TABLE_NAME_COLUMN("name"),
     CHAPTER_TABLE_WORLD_COLUMN("world");
    private String title;

    DatabaseInfo(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
