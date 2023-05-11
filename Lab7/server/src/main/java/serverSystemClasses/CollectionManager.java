package serverSystemClasses;

import database.DatabaseHandleConnect;
import database.DatabaseHandleGetter;
import mainCollection.collection.SpaceMarine;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Date;

public class CollectionManager {
    private static final Date time = new Date();
    private DatabaseHandleConnect databaseHandleConnect;
    private static final Date dateOfCollection = new Date((time.getTime()));
    public static Date getDateOfCollection(){
        return dateOfCollection;
    }
    /**
     * @param minOfHealth - наименьшее хп в коллекции
     * @param maxOfHealth  - наибольшее хп в коллекции
     */
    private long minOfHealth = Long.MAX_VALUE;
    private long maxOfHealth = 0;

    public long getMaxOfHealth() {
        return maxOfHealth;
    }

    public long getMinOfHealth() {
        return minOfHealth;
    }
    public DatabaseHandleConnect getDatabaseHandleConnect(){
        return databaseHandleConnect;
    }
    private ArrayDeque<SpaceMarine> collectionMarines = new ArrayDeque<>();

    public ArrayDeque<SpaceMarine> getCollectionMarines() {
        return collectionMarines;
    }


    public void setCollectionMarines(ArrayDeque<SpaceMarine> collection) {
        this.collectionMarines = collection;
    }
    public void updateHealthBoarders(){
        for(SpaceMarine element: collectionMarines){
            if(element.getHealth() > maxOfHealth){
                maxOfHealth = element.getHealth();
            }
            else if (element.getHealth() <  minOfHealth){
                minOfHealth = element.getHealth();
            }
        }
    }
    public CollectionManager(DatabaseHandleConnect databaseHandleConnect){
        this.databaseHandleConnect = databaseHandleConnect;

        try {
            collectionMarines = DatabaseHandleGetter.getCollection(databaseHandleConnect);
        } catch (SQLException e) {
            System.out.println("Не удалось скачать коллекцию");
        }
    }
    public SpaceMarine getById(Long id) {
        return collectionMarines.stream().filter(marine -> marine.getId().equals(id)).findFirst().orElse(null);
    }
}

