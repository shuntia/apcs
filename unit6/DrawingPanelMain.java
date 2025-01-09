import javax.swing.JFrame;

public class DrawingPanelMain {
    public static void main(String[] args) {
		JFrame window = new JFrame("Drawing Panel");
		window.setContentPane( new DrawingPanel() );
        window.setSize(700,500);
        window.setLocation(150,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
