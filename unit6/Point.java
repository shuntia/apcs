public class Point {
    public int x;
    public int y;
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Point diff(Point p){
        return new Point(x-p.x, y-p.y);
    }
}
