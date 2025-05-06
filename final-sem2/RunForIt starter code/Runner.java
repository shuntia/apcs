
public class Runner {

	public static int UP = -1, LEFT = 0, DOWN = 1, RIGHT = 2;
	private int x, y, width, height, speed, direction;
	private boolean isCaught = false;
	
	public Runner(int x, int y, int width, int height, int direction, int speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.direction = direction;
		this.speed = speed;
	}
	
	public void updatePosition() {
		if (direction % 2 == 0)
			x += (direction - 1) * speed;
		else
			y += direction * speed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setCaught(boolean isCaught) {
		this.isCaught = isCaught;
	}
	
	public boolean isCaught() {
		return isCaught;
	}
	
	public boolean containsPoint(int px, int py) {
		return (x <= px && px <= x + width && y <= py && py <= y + height);
	}
}
