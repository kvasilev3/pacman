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
	
	public void setX(int givenX) {
		x = givenX;
	}
	
	public void setY(int givenY) {
		y = givenY;
	}
	
	protected void move() {
		
	}

	public Image getImage() {
		return null;
	}
}
