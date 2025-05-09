import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class CheckersView extends JPanel {

    private JButton newGameButton; // Button for starting a new game.

    private JButton resignButton; // Button that a player can use to end
                                  // the game by resigning.

    private JLabel message; // Label for displaying messages to the user.

    public CheckersView(CheckersModel model) {
        super();
        setBackground(Color.BLACK);

        // ***so, these were declared in the outer class, but we can still see them here
        System.err.println("Packing");
        resignButton = new JButton("Resign");
        newGameButton = new JButton("New Game");
        message = new JLabel("", JLabel.CENTER);
        message.setFont(new Font("Serif", Font.BOLD, 14));
        message.setForeground(Color.green);
        message.setText("Red:  Make your move.");
        newGameButton.setEnabled(false);
        resignButton.setEnabled(true);
        repaint();

    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public String getNewGameButtonText() {
        return newGameButton.getText();
    }

    public JButton getResignButton() {
        return newGameButton;
    }

    public String getResignButtonText() {
        return newGameButton.getText();
    }

    public void setMessageText(String s) {
        message.setText(s);
    }
}
