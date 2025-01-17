import java.awt.Graphics;

public class Pixel implements Drawable{
    int x,y;
    java.awt.Color color;
    private final static int BLACK = 0,
            RED = 1,     
            GREEN = 2,   
            BLUE = 3, 
            CYAN = 4,   
            MAGENTA = 5,
            YELLOW = 6;
    public Pixel(int x, int y, java.awt.Color color){
        this.x=x;
        this.y=y;
        this.color=color;
    }
    public Pixel(int x, int y, int color){
        this.x=x;
        this.y=y;
        switch(color){
            case BLACK -> this.color=java.awt.Color.BLACK;
            case RED -> this.color=java.awt.Color.RED;
            case GREEN -> this.color=java.awt.Color.GREEN;
            case BLUE -> this.color=java.awt.Color.BLUE;
            case CYAN -> this.color=java.awt.Color.CYAN;
            case MAGENTA -> this.color=java.awt.Color.MAGENTA;
            case YELLOW -> this.color=java.awt.Color.YELLOW;
        }
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, 1, 1);
    }
}
