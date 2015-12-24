import java.awt.*;
import java.util.List;

public class Blob {
    int id;
    Color color;

    private int x_low;
    private int x_high;
    private int y_low;
    private int y_high;

    public Blob(int id, Color color){
        this.id = id;
        this.color = color;

        x_low = Integer.MAX_VALUE;
        x_high = Integer.MIN_VALUE;
        y_low = Integer.MAX_VALUE;
        y_high = Integer.MIN_VALUE;
    }

    public void update(int x, int y){
        if(x > x_high) x_high = x;
        if(x < x_low)  x_low = x;
        if(y > y_high) y_high = y;
        if(y < y_low)  y_low = y;
    }

    public Rectangle getBounds(){
        return new Rectangle(x_low,y_low,x_high-x_low,y_high-y_low);
    }


}
