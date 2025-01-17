import java.awt.Color;
class Rect implements Drawable{
    int xoffset=0;
    int yoffset=0;
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
        int renderX=x+xoffset;
        int renderY=y+yoffset;
        int renderW=Math.abs(width);
        int renderH=Math.abs(height);
        if(width<0){
            renderX+=width;
            renderW=-width;
        }
        if(height<0){
            renderY+=height;
            renderH=-height;
        }
        g.setColor(color);
        g.fillRect(renderX, renderY, renderW, renderH);
        g.setColor(Color.BLACK);
        g.drawRect(renderX, renderY, renderW, renderH);
    }
    public void moveTo(int x, int y){
        xoffset=0;
        yoffset=0;
        this.x=x;
        this.y=y;
    }
    public void offset(int dx, int dy){
        xoffset=dx;
        yoffset=dy;
    }
    public void resize(int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    public void setCorners(int x0, int y0, int x1, int y1){
        x=x0;
        y=y0;
        width=x1-x0;
        height=y1-y0;
    }
    public Point getCoord(){
        return new Point(x, y);
    }
    public boolean contains(Point p){
        return x<=p.x && p.x<=x+width && y<=p.y && p.y<=y+height;
    }
    public boolean contains(int x, int y){
        return contains(new Point(x, y));
    }
    public void finalizeOffset(){
        x+=xoffset;
        y+=yoffset;
        xoffset=0;
        yoffset=0;
    }
}