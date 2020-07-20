package pacmanGame;

import java.awt.Image;

public class Sprite {
	protected int x;
	protected int y;
	protected Direction direction;
	public String secondaryMode = "SCATTER";
	
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
	
	protected void move(Sprite pacman, Sprite blinky) {
		
	}
	
	public Image getImage(double frightenedModeStart, double timeCount) {
		return null;
	}
	
	public String getMode() {
		return null;
	}
	
	public void setMode(String givenMode) {
	}

	public int getTargetX(Sprite pacman, Sprite blinky) {
		return 0;
	}

	public int getTargetY(Sprite pacman, Sprite blinky) {
		return 0;
	}
	
	public void resetSprite() {
		init();
	}
	
	protected void init() {
	}

	public void setSecondaryMode(String mode) {
		secondaryMode = mode;
	}
	
	public String getSecondaryMode() {
		return secondaryMode;
	}
}
