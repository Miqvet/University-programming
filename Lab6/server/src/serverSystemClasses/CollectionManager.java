package serverSystemClasses;

import exceptions.ValidateException;
import mainCollection.collection.SpaceMarine;

import java.util.ArrayDeque;
import java.util.Date;

import static document.ReadFromDocument.deserializeXML;

public class CollectionManager {
    private static Date time = new Date();
    private static Date dateOfCollection = new Date((time.getTime()));
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
    private ArrayDeque<SpaceMarine> collectionMarines = new ArrayDeque<>();

    public ArrayDeque<SpaceMarine> getCollectionMarines() {
        return collectionMarines;
    }


    public void setCollectionMarines(ArrayDeque<SpaceMarine> collection) {
        this.collectionMarines = collection;
    }
    private String filename;
    public void setFilename(String file){
        filename = file;
    }
    public String getFilename(){
        return filename;
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
    public CollectionManager(String filename){
        setFilename(filename);
        deserializeXML(this);
    }
}

