package mainCollection.collection;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import exceptions.ValidateException;

import java.io.Serial;
import java.io.Serializable;

@JsonPropertyOrder({ "chapter_name", "chapter_world" })
public class Chapter implements Serializable {
    @Serial
    private static final long serialVersionUID = 10L;
    private String name; //Поле не может быть null, Строка не может быть пустой

    private String world; //Поле не может быть null
    public Chapter(String name, String world){
        setName(name);
        setWorld(world);
    }
    public Chapter(){}
    @Override
    public String toString(){
        return "Глава этого корабля " + this.name + " Мир этого корабля "+ this.world ;
    }

    public void setName(String name) {
        if (name == null){
            throw new ValidateException("В конструктор класса глава были переданы не валидные значения имени. Имя не должно быть null");
        }
        this.name = name;
    }

    public void setWorld(String world) {
        if (world == null){
            throw new ValidateException("В конструктор класса глава были переданы не валидные значения мира. Мир не должен быть null");
        }
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public String getWorld() {
        return world;
    }
}
