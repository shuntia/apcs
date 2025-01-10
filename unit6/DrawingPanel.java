
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.JPanel;
public class DrawingPanel extends JPanel{
    private volatile int counter = 0;
    public volatile ArrayList<Rect> rects=new ArrayList<Rect>();
    class Rect{
        int x;
        int y;
        int width;
        int height;
        Color color;
        public Rect(int x, int y, int width, int height, Color color){
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
            this.color=color;
        }
        public void draw(java.awt.Graphics g){
            g.setColor(color);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
        }
    }
    public DrawingPanel(){
        super();
    }
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        counter++;
        int x=(int)(Math.random()*300);
        int y=(int)(Math.random()*300);
        int w=(int)(Math.random()*200)+20;
        int h=(int)(Math.random()*200)+20;
        Color c=randomColor();
        Rect r=new Rect(x, y, w, h, c);
        rects.add(r);
        for(Rect rect:rects){
            rect.draw(g);
        }
        g.drawString("System called paintComponent "+counter+" times.", 100,100);
    }
    Color randomColor(){
        return new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
    }
}
