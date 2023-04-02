package document;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import mainCollection.collection.SpaceMarine;
import serverSystemClasses.CollectionManager;
import utilities.Output;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;

/**
 * Класс для записи элементов массива в XML
 * @autor Ляшенко Никита
 * @version 1
 */
public class WriteToDocument {
    public static void writeToXML(CollectionManager collectionManager) {
        if(collectionManager.getFilename() != null){
            String filename = collectionManager.getFilename();
            ArrayDeque<SpaceMarine> collection = collectionManager.getCollectionMarines();
            XmlMapper xmlMapper = new XmlMapper();
            try(FileWriter writer = new FileWriter(filename)){
                String xmlString = xmlMapper.writeValueAsString(collection);
                writer.write(xmlString);
            } catch (IOException e){
                Output.println("Нарушена целостность файлов, поэтому сохранённая коллекция была либо проигнорирована, либо не найдена");
                Output.println("Пожалуйста переустановите программу, для корректной работы");
            }
        }
    }
}

