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
	
	public int getScatterX() {
		return 0;
	}
	
	public int getScatterY() {
		return 0;
	}
	
	public int getChaseX() {
		return 0;
	}
	
	public int getChaseY() {
		return 0;
	}

	public Image getImage() {
		return null;
	}
	
	public String getMode() {
		return null;
	}
	
	public void setMode(String givenMode) {
	}
}
