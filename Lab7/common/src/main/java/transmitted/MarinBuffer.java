package transmitted;

import mainCollection.collection.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @param name        Поле не может быть null, Строка не может быть пустой
 * @param health      Значение поля должно быть больше 0
 * @param coordinates Поле не может быть null
 * @param category    Поле не может быть null
 * @param weaponType  Поле не может быть null
 * @param meleeWeapon Поле не может быть null
 * @param chapter     Поле не может быть null
 */
public record MarinBuffer(String name, Long health, Coordinates coordinates, AstartesCategory category,
                          Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public ArrayList<Object> getAll() {
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
