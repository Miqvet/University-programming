package document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import exceptions.CommandRuntimeException;
import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для чтения XML
 * Ляшенко Никита
 * @version 1
 */
public class ReadFromDocument {
    /**
     * Метод отвечающий за чтение из файла
     */
    public static void deserializeXML(CollectionManager collectionManager){
        try{
            String filename = collectionManager.getFilename();
            XmlMapper xmlMapper = new XmlMapper();
            ArrayDeque<SpaceMarine> collection = new ArrayDeque<>();
            File xmlDocument = new File(filename);
            try(Scanner sc = new Scanner(xmlDocument)) {
                if(sc.hasNext()){
                    String xml = sc.nextLine();
                    collection = xmlMapper.readValue(xml, new TypeReference<>(){});
                }
                collectionManager.setCollectionMarines(collection);
                collectionManager.updateHealthBoarders();
            }catch (FileNotFoundException | JsonProcessingException e) {
                collectionManager.setCollectionMarines(new ArrayDeque<>());
                collectionManager.setFilename(null);
            }
        } catch(Exception e){
            collectionManager.setCollectionMarines(new ArrayDeque<>());
            collectionManager.setFilename(null);
        }
    }
}
