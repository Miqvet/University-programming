package mainCollection;

import mainCollection.collection.SpaceMarine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;



/**
 * Класс для сотировки элементов коллекции
 * @autor Ляшенко Никита
 * @version 1
 */
public class SortCollection {
    /**
     * метод для сортировки нашей основной коллекции
     * @param collection - основная коллекция
     */
    public static void sortCollect(ArrayDeque<SpaceMarine> collection){
        /**
         * создание списка и его сортировка
         */
        ArrayList<SpaceMarine> buffer = new ArrayList<>(collection);
        Collections.sort(buffer);
        /**
         * обнуление основной коллекции и внесение в неё отсортированных значений
         */
        collection.clear();
        collection.addAll(buffer);
    }
}
