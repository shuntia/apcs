import java.awt.*;
import java.awt.event.*;

public class SimplePaintMenu extends MenuBar implements ActionListener {
    SimplePaintPanel panel;
    public SimplePaintMenu(SimplePaintPanel panel) {
        this.panel = panel;
        Menu simplePaint= new Menu("Simple Paint");
        Menu edit= new Menu("Edit");
        MenuItem undo= new MenuItem("Undo");
        MenuItem quit= new MenuItem("Quit");
        simplePaint.add(quit);
        edit.add(undo);
        add(simplePaint);
        add(edit);
        quit.addActionListener(this);
        undo.addActionListener(this);
    }
    public void actionPerformed(ActionEvent evt) {
        String command = evt.getActionCommand();
        if (command.equals("Quit")) {
            System.exit(0);
        }
        if (command.equals("Undo")) {
            panel.undoStroke();
        }
    }
}
