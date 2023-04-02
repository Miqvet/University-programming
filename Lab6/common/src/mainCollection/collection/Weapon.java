package mainCollection.collection;
import exceptions.ValidateException;

import java.io.Serializable;
import java.util.ArrayList;

public enum Weapon implements Serializable {
    HEAVY_BOLTGUN("HEAVY_BOLTGUN"),
    PLASMA_GUN("PLASMA_GUN "),
    COMBI_PLASMA_GUN("COMBI_PLASMA_GUN"),
    MISSILE_LAUNCHER("MISSILE_LAUNCHER");
    private final String title;
    /** вывод всех типов данных */
    public static ArrayList dataWeapen(){
        ArrayList<String> weaponData = new ArrayList<>();
        for(Weapon element: Weapon.values()){
            weaponData.add(element.title);
        }
        return weaponData;
    }
    /** перевод строки в нужный тип данных */
    public static Weapon selectWeapon(String sc) throws ValidateException{
        sc = sc.toUpperCase();
        if(sc.equals("HEAVY_BOLTGUN") | sc.equals("1")){
            return Weapon.HEAVY_BOLTGUN;
        } else if (sc.equals("PLASMA_GUN") | sc.equals("2")) {
            return Weapon.PLASMA_GUN;
        } else if (sc.equals("COMBI_PLASMA_GUN") | sc.equals("3")) {
            return Weapon.COMBI_PLASMA_GUN;
        } else if (sc.equals("MISSILE_LAUNCHER") | sc.equals("4")) {
            return Weapon.MISSILE_LAUNCHER;
        }else{
            throw new ValidateException("Введённый тип оружия не был объявлен ранее!");
        }
    }
    Weapon(String title){
        this.title = title;
    }
}
