package transmitted;

import mainCollection.collection.*;

import java.io.Serializable;
import java.util.ArrayList;

public class MarinBuffer implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long health; //Значение поля должно быть больше 0
    private Coordinates coordinates; //Поле не может быть null
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null

    public MarinBuffer(String name, Long health, Coordinates coordinates, AstartesCategory category,
                     Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.name = name;
        this.health = health;
        this.coordinates = coordinates;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }
    public String getName() {
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public Long getHealth() {
        return health;
    }
    public AstartesCategory getCategory() {
        return category;
    }
    public Weapon getWeaponType() {return weaponType;}
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }
    public Chapter getChapter() {
        return chapter;
    }
    public ArrayList<Object> getAll(){
        ArrayList<Object> list = new ArrayList<>();
        list.add(name);
        list.add(health);
        list.add(coordinates.getX());
        list.add(coordinates.getY());
        list.add(category);
        list.add(weaponType);
        list.add(meleeWeapon);
        list.add(chapter.getName());
        list.add(chapter.getWorld());
        return list;
    }
}
