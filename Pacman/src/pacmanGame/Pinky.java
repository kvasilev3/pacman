package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Pinky extends Ghost {

	private Image[] pinkGhost = {
			createImageIcon("/resources/pinky_1.png").getImage(),
			createImageIcon("/resources/pinky_2.png").getImage()
	};
	
	public Pinky() {
		init();
	}
	
	@Override
	protected void init() {
		//Starting Position
		x = 70;
		y = 69;
		direction = Direction.Up;
		
		scatterX = 12;
		scatterY = -18;
		inGhostHouseX = 70;
		inGhostHouseY = 75 + 5;
		canTurnGhostHouse = false;
		
		mode = "GHOST_HOUSE";
	}
	
	@Override
	public int getTargetX(Sprite pacman, Sprite blinky) {
		if (getMode() == "SCATTER") {
			return scatterX;
		} else if (getMode() == "EATEN") {
			return ghostHouseX;
		} else if (getMode() == "GHOST_HOUSE") {
			return inGhostHouseX;
		} else {
			if (pacman.direction == Direction.Up) {
				chaseX = pacman.getX() - (4 * 5);
			} else if (pacman.direction == Direction.Left) {
				chaseX = pacman.getX() - (4 * 5);
			} else if (pacman.direction == Direction.Down) {
				chaseX = pacman.getX();
			} else if (pacman.direction == Direction.Right) {
				chaseX = pacman.getX() + (4 * 5);
			}
			return chaseX;
		}
	}
	
	@Override
	public int getTargetY(Sprite pacman, Sprite blinky) {
		if (getMode() == "SCATTER") {
			return scatterY;
		} else if (getMode() == "EATEN") {
			return ghostHouseY;
		} else if (getMode() == "GHOST_HOUSE") {
			return inGhostHouseY;
		} else {
			if (pacman.direction == Direction.Up) {
				chaseY = pacman.getY() - (4 * 5);
			} else if (pacman.direction == Direction.Left) {
				chaseY = pacman.getY();
			} else if (pacman.direction == Direction.Down) {
				chaseY = pacman.getY() + (4 * 5);
			} else if (pacman.direction == Direction.Right) {
				chaseY = pacman.getY() ;
			}
			return chaseY;
		}
	}
	
	@Override
	public Image getImage(double frightenedModeStart, double timeCount) {
		if (mode == "FRIGHTENED") {
			return getFrightenedImage(frightenedModeStart, timeCount);
		} else if (mode == "EATEN") {
			return null;
		}else {
			if (Ghost.i >= pinkGhost.length) {
				Ghost.i = 0;
			}
		}
		return pinkGhost[(int) Ghost.i];
	}
}