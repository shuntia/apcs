import java.awt.BorderLayout;
import javax.swing.*;

public class Main {
    public static ScorePanel scorePanel = new ScorePanel();
    public static JFrame window = new JFrame("Sub Killer Game");
    public static JPanel content = new JPanel();
    public static Listener l=new Listener();
    public static Panel mainPanel = new Panel(l);
    public static JMenuBar menuBar = new JMenuBar();
    public static JMenu subKillerMenu = new JMenu("Sub Killer");
    public static JMenu optionsMenu = new JMenu("Options");
    public static JMenuItem about = new JMenuItem("About");
    public static JMenuItem quit = new JMenuItem("Quit");
    public static JMenuItem restart = new JMenuItem("Restart");

    public static void main(String[] args) {
        subKillerMenu.add(about);
        subKillerMenu.addSeparator();
        subKillerMenu.add(quit);
        optionsMenu.add(restart);
        menuBar.add(subKillerMenu);
        menuBar.add(optionsMenu);
        about.addActionListener(l);
        quit.addActionListener(l);
        restart.addActionListener(l);
        window.setJMenuBar(menuBar);
        content.setLayout(new BorderLayout());
        content.add(mainPanel, BorderLayout.CENTER);
        content.add(scorePanel, BorderLayout.SOUTH);
        window.setJMenuBar(menuBar);
        window.setContentPane(content);
        window.setSize(600, 480);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);  // User can't change the window's size.
        window.setVisible(true);
    }
}
