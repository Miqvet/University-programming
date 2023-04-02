package mainCollection.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import exceptions.CommandRuntimeException;
import exceptions.ValidateException;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Long.parseLong;

@JsonPropertyOrder({ "id", "name", "health","coordinates","category","weaponType","meleeWeapon","chapter","creationDate" })
public class SpaceMarine implements Comparable<SpaceMarine>{
    static Date time = new Date();
    public static Long idPrototip = Long.valueOf(1);
    public static void updateidPrototip(ArrayDeque<SpaceMarine> collection){
        for(SpaceMarine element: collection){
            if(element.getId() > idPrototip){
                idPrototip = element.getId();
            }
        }
        idPrototip += 1;
    }
    /** Поле id */
    @JsonProperty("id")
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /** Поле имя */
    @JsonProperty("name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    /** Поле жизнь */
    @JsonProperty("health")
    private Long health; //Значение поля должно быть больше 0
    /** Поле координаты корябля */
    @JsonProperty("coordinates")
    private Coordinates coordinates; //Поле не может быть null
    /** Поле категория корабля */
    @JsonProperty("category")
    private AstartesCategory category; //Поле не может быть null
    /** Поле тип оружия корабля */
    @JsonProperty("weaponType")
    private Weapon weaponType; //Поле не может быть null
    /** Поле тип ближнего оружия корабля */
    @JsonProperty("meleeWeapon")
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    /** Поле принадлежность корбля */
    @JsonProperty("chapter")
    private Chapter chapter; //Поле не может быть null
    /** Поле дата создания корабля */
    @JsonProperty("creationDate")
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private final int NAME_INDEX = 0;
    private final int HEALTH_INDEX = 1;
    private final int COORDINATES_INDEX_1 = 2;
    private final int COORDINATES_INDEX_2 = 3;
    private final int CATEGORY_INDEX = 4;
    private final int WEAPONTYPE_INDEX = 5;
    private final int MELEEWEAPON_INDEX = 6;
    private final int CHAPTER_INDEX_1 = 7;
    private final int CHAPTER_INDEX_2 = 8;
    // порядок ввода данных в конструктор строго по полям сверху вниз
    /** * Конструктор - создание нового объекта с определенными значениями
     * @param elementData - массив данных объекта*/
    public SpaceMarine(ArrayList<Object> elementData) throws CommandRuntimeException, ValidateException {
        this.id = idPrototip;
        idPrototip += 1;
        setName((String) elementData.get(NAME_INDEX));
        setHealth((Long) elementData.get(HEALTH_INDEX));
        setCoordinates(new Coordinates(
                (Long) elementData.get(COORDINATES_INDEX_1),
                (Integer) elementData.get(COORDINATES_INDEX_2))
        );
        setAstralcategory((AstartesCategory) elementData.get(CATEGORY_INDEX));
        setWeaponType((Weapon) elementData.get(WEAPONTYPE_INDEX));
        setMeleeWeapon((MeleeWeapon) elementData.get(MELEEWEAPON_INDEX));
        setChapter(new Chapter((String) elementData.get(CHAPTER_INDEX_1) ,(String) elementData.get(CHAPTER_INDEX_2)));
        setCreationDate();
    }

    public SpaceMarine(){}

    @Override
    public String toString(){
        return  "Id элемента = " + this.id + "\n Название этого корабля = " + this.name + "\n Здоровье этого корабля = " + this.health + "\n Координаты этого корабля  = " + this.coordinates.toString() + "\n Категория этого судна" + this.category + "\n Оружие этого корабля - " + this.weaponType +  "\n Ближнее Оружие этого корабля - " + this.meleeWeapon +  "\n " + this.chapter.toString() ;
    }

    /**
     * Блок сеттеров в которые могут быть переданы только
     * @throws ValidateException
     */
    public void setName(String name)throws ValidateException {
        if (name == null){
            throw new ValidateException("Валидность данных нарушена. У корабля должно быть название");
        }
        this.name = name;
    }
    public void setHealth(Long health)throws ValidateException {
        if (health == null | health <= 0){
            throw new ValidateException("Валидность данных нарушена. HP корабля должно быть больше нуля и меньше " +  Long.MAX_VALUE);
        }
        this.health = health;
    }
    public void setCoordinates(Coordinates coordinates) throws ValidateException {
        if (coordinates == null){
            throw new ValidateException("Валидность данных нарушена. У корабля должны быть координаты его местоположения");
        }
        this.coordinates = coordinates;
    }
    public void setAstralcategory(AstartesCategory category) throws ValidateException{
        if (category == null){
            throw new ValidateException("Категория корабля не может быть пустой. Проверьте валидность данных");
        }
        this.category = category;
    }
    public void setWeaponType(Weapon weaponType) throws ValidateException {
        if (weaponType== null){
            throw new ValidateException("В тип оружия корабля не может быть записано null. Проверьте валидность данных");
        }
        this.weaponType = weaponType;
    }
    public void setMeleeWeapon(MeleeWeapon meleeWeapon) throws ValidateException {
        if (meleeWeapon == null){
            throw new ValidateException("В ближнее оружие корабля не может быть записано null. Проверьте валидность данных");
        }
        this.meleeWeapon = meleeWeapon;
    }
    public void setChapter(Chapter chapter) throws ValidateException{
        if (chapter == null){
            throw new ValidateException("В главу корабля не может быть записано null. Проверьте валидность данных");
        }
        this.chapter = chapter;
    }
    public String getName() {return this.name;}
    public Long getId(){
        return this.id;
    }
    public long getHealth() {
        return this.health;
    }
    public Coordinates getCoordinates() {
        return this.coordinates;
    }
    public MeleeWeapon getMeleeWeapon() {
        return this.meleeWeapon;
    }
    public Weapon getWeaponType() {
        return this.weaponType;
    }
    public Chapter getChapter() {
        return this.chapter;
    }
    public AstartesCategory getCategory() {
        return this.category;
    }


    @Override
    public int compareTo(SpaceMarine o) {
        return this.name.compareTo(o.getName());
    }

    public void setCreationDate() {
        this.creationDate = new Date((time.getTime()));
    }
}
