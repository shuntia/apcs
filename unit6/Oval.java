
import java.awt.Color;

public class Oval implements Drawable{
    int x;
    int y;
    int width;
    int height;
    Color color;
    public Oval(int x, int y, int width, int height, Color color){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.color=color;
    }
    public void draw(java.awt.Graphics g){
        g.setColor(color);
        g.fillOval(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, width, height);
    }
    
}
