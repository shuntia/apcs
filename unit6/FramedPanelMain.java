import java.awt.Color;
import javax.swing.JFrame;

public class FramedPanelMain {
    public static void main(String[] args) {
		JFrame window = new JFrame("Framed Panel");
		window.setContentPane( new FramedPanel(Color.RED) );
        window.setSize(700,500);
        window.setLocation(150,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
