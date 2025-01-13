import java.awt.Color;
class Rect implements Drawable{
    int x;
    int y;
    int width;
    int height;
    Color color;
    public Rect(int x, int y, int width, int height, Color color){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.color=color;
    }
    public void draw(java.awt.Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
    public void resize(int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    public void setCorners(int x0, int y0, int x1, int y1){
        x=Math.min(x0, x1);
        y=Math.min(y0, y1);
        width=Math.abs(x1-x0);
        height=Math.abs(y1-y0);
    }
    public Point getCoord(){
        return new Point(x, y);
    }
}