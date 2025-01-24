import java.awt.*;
public class Boat {
    Panel p;
    int centerX, centerY;  // Current position of the center of the boat.
    Boat(Panel pa) { // Constructor centers the boat horizontally, 80 pixels from top.
        p=pa;
        centerX = p.getHeight()/2;
        centerY = 80;
    }
    void updateForNewFrame() { // Makes sure boat has not moved off screen.
        if (centerX < 0)
            centerX = 0;
        else if (centerX > p.getHeight())
            centerX = p.getHeight();
    }
    void draw(Graphics g) {  // Draws the boat at its current location.
        g.setColor(Color.BLUE);
        g.fillRoundRect(centerX - 40, centerY - 20, 80, 40, 20, 20);
    }
}
