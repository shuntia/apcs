import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.*;

public class Graphicsmgr extends JFrame implements KeyResponsive {
    final boolean isGUI;
    HashMap<String, JComponent> components = new HashMap<>();
    public Position position = new Position();
    public BufferedImage UILayer, MapLayer, OverlayLayer, frameBuffer;
    public static boolean ismap = true;
    public JPanel panel;
    public JFrame frame;

        public Graphicsmgr(boolean isGUI) {
        this.isGUI = isGUI;
        frame = new JFrame();
        frame.setTitle("Graphics Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Set layout manager
    
        if (isGUI) {
            makePanel();
            frame.add(panel, BorderLayout.CENTER);
            components.put("Tilemap", new JLabel((String) null));
            components.forEach((name, component) -> {
                component.setName(name);
                component.setVisible(true);
                panel.add(component); // Add to panel instead of frame
            });
        } else {
            makePanel();
            frame.add(panel, BorderLayout.CENTER);
            components.put("Tilemap", new JTextArea());
            components.forEach((name, component) -> {
                component.setName(name);
                component.setVisible(true);
                panel.add(component); // Add to panel instead of frame
            });
            JTextArea ta = ((JTextArea) components.get("Tilemap"));
            ta.setEditable(false);
            ta.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        }
    
        components.get("Tilemap").setBounds(0, 0, 800, 800);
        components.get("Tilemap").setVisible(true);
    
        frame.pack(); // Ensure all components are properly sized
        frame.setVisible(true); // Make frame visible
    }
    
    public final void makePanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Set layout manager for panel
        panel.setVisible(true);
    }
    public void updateGUI() {
        System.out.println("Updating GUI");
        BufferedImage image = Tilemapmgr.drawmap();
        Graphics g = panel.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    public void updateTUI() {
        components.forEach((_, component) -> {
            if (component instanceof JTextArea textArea) {
                textArea.setText(Tilemapmgr.drawcmap().toString());
            }
        });
    }

    public void draw() {
        if (isGUI) {
            updateGUI();
        } else {
            updateTUI();
        }
    }

    public JComponent getComponent(String name) {
        return components.get(name);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> Tilemapmgr.position.x -= 1;
            case KeyEvent.VK_RIGHT -> Tilemapmgr.position.x += 1;
            case KeyEvent.VK_UP -> Tilemapmgr.position.y -= 1;
            case KeyEvent.VK_DOWN -> Tilemapmgr.position.y += 1;
        }
    }

    public void move(Object component, int x, int y){
        if (component instanceof JComponent jc) {
            jc.setLocation(x, y);
        }
        if (component instanceof String){
            JComponent jc = components.get(component);
            jc.setLocation(x, y);
        }
    }
}