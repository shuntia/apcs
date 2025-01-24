import java.awt.*;
class Submarine {
    Panel p;
    int centerX, centerY; // Current position of the center of the sub.
    boolean isMovingLeft; // Tells whether the sub is moving left or right
    boolean isExploding;  // Set to true when the sub is hit by the bomb.
    int explosionFrameNumber;  // If the sub is exploding, this is the number
                               //   of frames since the explosion started.
    Submarine(Panel pa) {  // Create the sub at a random location 40 pixels from bottom.
        p=pa;
        centerX = (int)(p.getWidth()*Math.random());
        centerY = p.getHeight() - 40;
        isExploding = false;
        isMovingLeft = (Math.random() < 0.5);
    }
    void updateForNewFrame() { // Move sub or increase explosionFrameNumber.
        if (isExploding) {
                // If the sub is exploding, add 1 to explosionFrameNumber.
                // When the number reaches 15, the explosion ends and the
                // sub reappears in a random position.
            explosionFrameNumber++;
            if (explosionFrameNumber == 15) { 
                centerX = (int)(p.getWidth()*Math.random());
                centerY = p.getHeight() - 40;
                isExploding = false;
                isMovingLeft = (Math.random() < 0.5);
            }
        }
        else { // Move the sub.
            if (Math.random() < 0.04) {  
                    // In one frame out of every 25, on average, the sub
                    // reverses its direction of motion.
                isMovingLeft = ! isMovingLeft; 
            }
            if (isMovingLeft) { 
                    // Move the sub 5 pixels to the left.  If it moves off
                    // the left edge of the panel, move it back to the left
                    // edge and start it moving to the right.
                centerX -= 5;  
                if (centerX <= 0) {  
                    centerX = 0; 
                    isMovingLeft = false; 
                }
            }
            else {
                    // Move the sub 5 pixels to the right.  If it moves off
                    // the right edge of the panel, move it back to the right
                    // edge and start it moving to the left.
                centerX += 5;         
                if (centerX > p.getWidth()) {  
                    centerX = p.getWidth();   
                    isMovingLeft = true; 
                }
            }
        }
    }
    void draw(Graphics g) {  // Draw sub and, if it is exploding, the explosion.
        g.setColor(Color.BLACK);
        g.fillOval(centerX - 30, centerY - 15, 60, 30);
        if (isExploding) {
                // Draw an "explosion" that grows in size as the number of
                // frames since the start of the explosion increases.
            g.setColor(Color.YELLOW);
            g.fillOval(centerX - 4*explosionFrameNumber,
                    centerY - 2*explosionFrameNumber,
                    8*explosionFrameNumber,
                    4*explosionFrameNumber);
            g.setColor(Color.RED);
            g.fillOval(centerX - 2*explosionFrameNumber,
                    centerY - explosionFrameNumber/2,
                    4*explosionFrameNumber,
                    explosionFrameNumber);
        }
    }
} // end nested class Submarine   