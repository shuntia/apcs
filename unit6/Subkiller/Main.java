import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Sub Killer Game");
        Panel content = new Panel();
        window.setContentPane(content);
        window.setSize(600, 480);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setResizable(false);  // User can't change the window's size.
        window.setVisible(true);
    }
}
