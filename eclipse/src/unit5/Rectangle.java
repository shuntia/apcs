// Source code is decompiled from a .class file using FernFlower decompiler.
package unit5;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle implements Drawable {
   private int x;
   private int y;
   private int width;
   private int height;
   private Color color;

   public Rectangle() {
      this.x = 0;
      this.y = 0;
      this.width = 50;
      this.height = 50;
      this.color = Color.BLACK;
   }

   public Rectangle(int width, int height) {
      this.width = width;
      this.height = height;
      this.color = Color.BLACK;
   }

   public Rectangle(int x, int y, int width, int height, Color color) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.color = color;
   }

   public void draw(Graphics g) {
      g.setColor(this.color);
      g.fillRect(this.x, this.y, this.width, this.height);
      g.setColor(Color.BLACK);
      g.drawRect(this.x, this.y, this.width, this.height);
   }

   public String toString() {
      int var10000 = this.x;
      return "Rectangle with x: " + var10000 + ", y: " + this.y + ", width: " + this.width + ", height: " + this.height + ", color: " + String.valueOf(this.color);
   }

   public int getX() {
      return this.x;
   }

   public void setX(int x) {
      if (x > 0) {
         this.x = x;
      }

   }

   public int getY() {
      return this.y;
   }

   public void setY(int y) {
      if (y > 0) {
         this.y = y;
      }

   }

   public int getWidth() {
      return this.width;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getHeight() {
      return this.height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public Color getColor() {
      return this.color;
   }

   public void setColor(Color color) {
      this.color = color;
   }
}
