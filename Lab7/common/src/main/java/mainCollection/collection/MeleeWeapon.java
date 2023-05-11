package mainCollection.collection;
import exceptions.ValidateException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public enum MeleeWeapon  implements Serializable {
    CHAIN_AXE("CHAIN_AXE"),
    MANREAPER("MANREAPER"),
    POWER_FIST("POWER_FIST");
    private final String title;
    /** вывод всех типов данных */
    public static ArrayList dataMeleeWeapon(){
        ArrayList<String> meleeWeaponData = new ArrayList<>();
        for(MeleeWeapon element: MeleeWeapon.values()){
            meleeWeaponData.add(element.title);
        }
        return meleeWeaponData;
    }
    /** перевод строки в нужный тип данных */
    public static MeleeWeapon selectMeleeWeapon(String sc) throws ValidateException{
        sc = sc.toUpperCase();
        if(sc.equals("CHAIN_AXE") | sc.equals("1")){
            return  MeleeWeapon.CHAIN_AXE;
        } else if (sc.equals("MANREAPER") | sc.equals("2")) {
            return MeleeWeapon.MANREAPER;
        } else if (sc.equals("POWER_FIST") | sc.equals("3")) {
            return MeleeWeapon.POWER_FIST;
        }else{
            throw new ValidateException("Введённый тип оружия не был объявлен ранее!");
        }
    }
    MeleeWeapon(String title) {
        this.title = title;
    }
    @Serial
    private static final long serialVersionUID = 7L;
}
