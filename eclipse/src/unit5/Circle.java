// Source code is decompiled from a .class file using FernFlower decompiler.
package unit5;

import java.awt.Graphics;

public class Circle implements Drawable {
   private int radius = (int)(Math.random() * 200.0);
   private int x = (int)(Math.random() * 200.0);
   private int y = (int)(Math.random() * 200.0);

   public Circle() {
   }

   public void draw(Graphics g) {
      g.drawOval(this.x, this.y, this.radius / 2, this.radius / 2);
   }
}
