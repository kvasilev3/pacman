package pacmanGame;

import java.awt.Image;

public class Sprite {
	protected int x;
	protected int y;
	protected Direction direction;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	protected void move() {
		
	}

	public Image getImage() {
		return null;
	}
}
