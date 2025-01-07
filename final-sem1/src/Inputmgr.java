import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;

public class Inputmgr implements KeyListener {
    private static final HashSet<String> keys = new HashSet<>();
    private static final ArrayList<KeyResponsive> keyResponsives = new ArrayList<>();

    @Override
    public void keyTyped(KeyEvent e) {
        for (KeyResponsive kr : keyResponsives) {
            kr.keyTyped(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
        String keyName = KeyEvent.getKeyText(e.getKeyCode());
        keys.add(keyName);
        for (KeyResponsive kr : keyResponsives) {
            kr.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String keyName = KeyEvent.getKeyText(e.getKeyCode());
        keys.remove(keyName);
        for (KeyResponsive kr : keyResponsives) {
            kr.keyReleased(e);
        }
    }

    public static void addKeyResponsive(KeyResponsive kr) {
        keyResponsives.add(kr);
    }

    public static void removeKeyResponsive(KeyResponsive kr) {
        keyResponsives.remove(kr);
    }

    public static void attach(Graphicsmgr gm) {
        gm.panel.addKeyListener(new Inputmgr());
    }
}