
import java.awt.*;
import javax.swing.JPanel;

public class Panel extends JPanel {
    Boat boat;
    Bomb bomb;
    Submarine sub;
    int subspeed=1;
    public Panel() {
        setBackground( new Color(0,200,0) ); 
        Listener listener = new Listener(this);
    }
    public Panel(Listener l){
        setBackground(new Color(0,200,0));
        l.attachToPanel(this);
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);  // Fill panel with background color, green.
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //*** Initialize if it's the first time
        // The reason we don't do it in the constructor is because we don't know the 
        // panel's getHeight() and getWidth() at that time, and the sub would be drawn off the screen
        if (boat == null) {
                // The first time that paintComponent is called, it assigns
                // values to the instance variables.
            boat = new Boat(this);
            sub = new Submarine(this);
            bomb = new Bomb(this);
        }

        if (hasFocus())
            g.setColor(Color.CYAN);
        else {
            g.setColor(Color.BLACK);
            g.drawString("CLICK TO ACTIVATE", 20, 30);
            g.setColor(Color.GRAY);
        }
        g.drawRect(0,0,getWidth()-1,getHeight()-1);  // Draw a 3-pixel border.
        g.drawRect(1,1,getWidth()-3,getHeight()-3);
        g.drawRect(2,2,getWidth()-5,getHeight()-5);

        boat.draw(g); //**Like a Rectangle, a Boat object knows how to draw itself
        sub.draw(g);
        bomb.draw(g);

    } // end paintComponent()
    public void restart(){
        boat=null;
        sub=null;
        bomb=null;
        Main.scorePanel.setScore(0);
        Main.scorePanel.getDifficultySlider().setValue(1);
    }
    public int getSubSpeed(){
        return subspeed;
    }
    public void setSubSpeed(int s){
        subspeed=s;
    }
}
