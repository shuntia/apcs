import java.awt.*;
class Bomb {
    Panel p;
    int centerX, centerY; // Current position of the center of the bomb.
    boolean isFalling;    // If true, the bomb is falling; if false, it
    Submarine sub;
    Boat b;
                         // is attached to the boat.
    Bomb(Panel pa) { // Constructor creates a bomb that is initially attached to boat.
        p=pa;
        sub=p.sub;
        b=p.boat;
        isFalling = false;
    }
    void updateForNewFrame() {  // If bomb is falling, take appropriate action.
        if (isFalling) {
            if (centerY > p.getHeight()) {
                    // Bomb has missed the submarine.  It is returned to its
                    // initial state, with isFalling equal to false.
                isFalling = false;
            }
            else if (Math.abs(centerX - sub.centerX) <= 36 &&
                    Math.abs(centerY - sub.centerY) <= 21) {
                    // Bomb has hit the submarine.  The submarine
                    // enters the "isExploding" state.
                sub.isExploding = true;
                sub.explosionFrameNumber = 1;
                isFalling = false;  // Bomb reappears on the boat.
            }
            else {
                    // If the bomb has not fallen off the panel or hit the
                    // sub, then it is moved down 10 pixels.
                centerY += 10;
            }
        }
    }
    void draw(Graphics g) { // Draw the bomb.
        if ( ! isFalling ) {  // If not falling, set centerX and centerY
                              // to show the bomb on the bottom of the boat.
            centerX = b.centerX;
            centerY = b.centerY + 23;
        }
        g.setColor(Color.RED);
        g.fillOval(centerX - 8, centerY - 8, 16, 16);
    }
} // end nested class Bomb