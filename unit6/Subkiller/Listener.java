
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Listener implements ActionListener, KeyListener, MouseListener, FocusListener, ChangeListener{
    Panel p;
    Timer timer;
    public Listener(Panel p){
        this.p=p;
        p.addMouseListener(this);
        p.addFocusListener(this);
        p.addKeyListener(this);
        timer=new Timer(30, this);
        timer.start();
        Main.scorePanel.getDifficultySlider().addChangeListener(this);
    }
    public Listener(){
        Main.scorePanel.getDifficultySlider().addChangeListener(this);
    }
    public void attachToPanel(Panel p){
        this.p=p;
        p.addMouseListener(this);
        p.addFocusListener(this);
        p.addKeyListener(this);
        timer=new Timer(30, this);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        String s=evt.getActionCommand();
        if(s==null){
            if (p.boat != null) {
                p.boat.updateForNewFrame();
                p.bomb.updateForNewFrame();
                for (int i = 0; i < p.getSubSpeed(); i++) {
                    p.sub.updateForNewFrame();
                }
            }
        }else{
            System.out.println("Action: "+s);
            switch (s){
                case "About"->JOptionPane.showMessageDialog(p,"This game rocks!!");
                case "Quit"->System.exit(0);
                case "Restart"->p.restart();
                default->System.err.println("??? Unknown Action source!!!");
            }
        }
        p.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        int code = evt.getKeyCode();  // Which key was pressed?
        switch (code) {
            case KeyEvent.VK_LEFT -> // Move the boat left.  (If this moves the boat out of the frame, its
                // position will be adjusted in the boat.updateForNewFrame() method.)
                p.boat.centerX -= 15;
            case KeyEvent.VK_RIGHT -> // Move the boat right.  (If this moves boat out of the frame, its
                // position will be adjusted in the boat.updateForNewFrame() method.)
                p.boat.centerX += 15;
            case KeyEvent.VK_DOWN -> {
                // Start the bomb falling, if it is not already falling.
                if ( p.bomb.isFalling == false )
                    p.bomb.isFalling = true;
            }
            default -> {
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
        timer.start();
        p.repaint();
    }

    @Override
    public void focusLost(FocusEvent e) {
        timer.stop();
        p.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        p.requestFocus();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() instanceof JSlider source){
            if(!source.getValueIsAdjusting()){
                int difficultylevel=source.getValue();
                p.setSubSpeed(difficultylevel);
            }
        }
    }
    
}
