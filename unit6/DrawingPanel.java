
import java.awt.Color;
import javax.swing.JPanel;
public class DrawingPanel extends JPanel{
    private volatile int counter = 0;
    public DrawingPanel(){
        super();
    }
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        counter++;
        int x=(int)(Math.random()*300);
        int y=(int)(Math.random()*300);
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 100, 100);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 100, 100);
        g.drawString("System called paintComponent "+counter+" times.", 100,100);
    }
}
