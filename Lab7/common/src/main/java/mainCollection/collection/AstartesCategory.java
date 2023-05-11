package mainCollection.collection;
import exceptions.ValidateException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public enum AstartesCategory  implements Serializable {
    AGGRESSOR("AGGRESSOR"),
    INCEPTOR("INCEPTOR"),
    SUPPRESSOR("SUPPRESSOR"),
    HELIX("HELIX");
    private final String title;

    AstartesCategory(String title) {
        this.title = title;
    }

    /** вывод всех типов данных */
    public static ArrayList dataAstartesCategory(){
        ArrayList<String> categoryData = new ArrayList<>();
        for(AstartesCategory element: AstartesCategory.values()){
            categoryData.add(element.title);
        }
        return categoryData;
    }
    /** перевод строки в нужный тип данных */
    public static AstartesCategory getAstartesCategory(String name) throws ValidateException {
        name = name.toUpperCase();
        if(name.equals("AGGRESSOR") | name.equals("1")){
            return AstartesCategory.AGGRESSOR;
        } else if (name.equals("INCEPTOR") | name.equals("2")) {
            return AstartesCategory.INCEPTOR;
        } else if (name.equals("SUPPRESSOR") | name.equals("3")) {
            return AstartesCategory.SUPPRESSOR;
        } else if (name.equals("HELIX") | name.equals("4")) {
            return AstartesCategory.HELIX;
        }else{
            throw new ValidateException("Введённая категория судна не была объявлена ранее!");
        }
    }
    @Serial
    private static final long serialVersionUID = 9L;
}
