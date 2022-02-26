package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Inky extends Ghost {

	private Image[] cyanGhost = {
			createImageIcon("/resources/inky_1.png").getImage(),
			createImageIcon("/resources/inky_2.png").getImage()
			};
	
	public Inky() {
		init();
	}
	
	@Override
	protected void init() {
		//Starting Position
		x = 59;
		y = 75;
		direction = Direction.Down;
		
		scatterX = 137;
		scatterY = 152;
		inGhostHouseX = 59;
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
			chaseX = pacman.getX();
			int blinkyX = blinky.getX();
			if (pacman.direction == Direction.Up) {
				chaseX = pacman.getX() - (2 * 5);
			} else if (pacman.direction == Direction.Left) {
				chaseX = pacman.getX() - (2 * 5);
			} else if (pacman.direction == Direction.Down) {
				chaseX = pacman.getX();
			} else if (pacman.direction == Direction.Right) {
				chaseX = pacman.getX() + (2 * 5);
			}
			
			int distanceToBlinky = chaseX - blinkyX;
			chaseX = chaseX + distanceToBlinky;
			
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
			int blinkyY = blinky.getY();
			if (pacman.direction == Direction.Up) {
				chaseY = pacman.getY() - (2 * 5);
			} else if (pacman.direction == Direction.Left) {
				chaseY = pacman.getY();
			} else if (pacman.direction == Direction.Down) {
				chaseY = pacman.getY() + (2 * 5);
			} else if (pacman.direction == Direction.Right) {
				chaseY = pacman.getY() ;
			}

			int distanceToBlinky = chaseY - blinkyY;
			chaseY = chaseY + distanceToBlinky;
			
			return chaseY;
		}
	}

	@Override
	public Image getImage(double frightenedModeStart, double timeCount) {
		if (mode == "FRIGHTENED") {
			return getFrightenedImage(frightenedModeStart, timeCount);
		} else if (mode == "EATEN") {
			return null;
		} else {
			if (Ghost.i >= cyanGhost.length) {
				Ghost.i = 0;
			}
		}
		return cyanGhost[(int) Ghost.i];
	}
}
