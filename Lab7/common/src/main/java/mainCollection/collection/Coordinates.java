package mainCollection.collection;

import exceptions.CommandRuntimeException;
import exceptions.ValidateException;

import java.io.Serial;
import java.io.Serializable;

public class Coordinates implements Serializable {
    private Long x;
    private Integer y;
    @Serial
    private static final long serialVersionUID = 8L;
    public Coordinates(Long x, Integer y) throws CommandRuntimeException {
        setX(x);
        setY(y);
    }
    @Override
    public String toString(){
        return this.x + " - координата по x " + this.y + " - координата по y" ;
    }
    public Coordinates(){}

    public void setX(Long x) {
        if (x == null){
            throw new ValidateException("Координата X не может быть null");
        }
        this.x = x;
    }

    public void setY(Integer y) {
        if (y == null){
            throw new ValidateException("Координата Y не может быть null");
        }
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
