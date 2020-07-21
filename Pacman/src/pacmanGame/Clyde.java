package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Clyde extends Ghost {
	
	private double i = 0;
	private Image[] yellowGhost = {
			new ImageIcon("Pacman/src/resources/clyde_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/clyde_2.png").getImage()
	};
	
	public Clyde() {
		init();
	}
	
	@Override
	protected void init() {
		//Starting Position
		x = 80;
		y = 75;
		direction = Direction.Down;
		
		scatterX = 2;
		scatterY = 152;
		inGhostHouseX = 80;
		inGhostHouseY = 75 + 5;
		
		mode = "GHOST_HOUSE";
	}
	
	@Override
	public int getTargetX(Sprite pacman, Sprite blinky) {
		if (getMode() == "SCATTER") {
			return scatterX;
		} else if (getMode() == "EATEN") {
			return ghostHouseX;
		} else if (getMode() == "GHOST_HOUSE") {
			return inGhostHouseY;
		} else {
			double distanceToPacman = Math.sqrt(Math.pow(pacman.getX() - getX(), 2) + Math.pow(pacman.getY() - getY(), 2));
			if (distanceToPacman <= (8 * 5)) {
				chaseX = scatterX;
			} else {
				chaseX = pacman.getX();
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
			double distanceToPacman = Math.sqrt(Math.pow(pacman.getX() - getX(), 2) + Math.pow(pacman.getY() - getY(), 2));
			if (distanceToPacman <= (8 * 5)) {
				chaseY = scatterY;
			} else {
				chaseY = pacman.getY();
			}
			return chaseY;
		}
	}
	
	@Override
	public Image getImage(double frightenedModeStart, double timeCount) {
		i += 0.5;
		if (mode == "FRIGHTENED") {
			return getFrightenedImage(frightenedModeStart, timeCount);
		} else if (mode == "EATEN") {
			return null;
		}else {
			if (i >= yellowGhost.length) {
				i = 0;
			}
		}
		return yellowGhost[(int) i];
	}
}