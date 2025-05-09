import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;

public class Checkers {
    public static void main(String[] args) {
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(model);
        CheckersController controller = new CheckersController(view, model);
        JFrame window = new JFrame("Checkers");
        window.setContentPane(view);
        window.pack();
        // ****this centers the window on the users screen
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screensize.width - window.getWidth()) / 2,
                (screensize.height - window.getHeight()) / 2);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }
}
