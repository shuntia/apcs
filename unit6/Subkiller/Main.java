import java.awt.BorderLayout;
import javax.swing.*;

public class Main {
    public static ScorePanel scorePanel = new ScorePanel();
    public static JFrame window = new JFrame("Sub Killer Game");
    public static JPanel content = new JPanel();
    public static Panel mainPanel = new Panel();
    public static void main(String[] args) {
        content.setLayout(new BorderLayout());
        content.add(mainPanel, BorderLayout.CENTER);
        content.add(scorePanel, BorderLayout.SOUTH);
        window.setContentPane(content);
        window.setSize(600, 480);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setResizable(false);  // User can't change the window's size.
        window.setVisible(true);
    }
}
