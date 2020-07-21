package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Blinky extends Ghost {

	private Image[] redGhost = {
			new ImageIcon("Pacman/src/resources/blinky_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/blinky_2.png").getImage()
			};

	public Blinky() {
		init();
	}
	
	@Override
	protected void init() {
		// Starting Position
		x = 70;
		y = 57;
		direction = Direction.Left;
		
		scatterX = 127;
		scatterY = -18;
		inGhostHouseX = 70;
		inGhostHouseY = 75;
		canTurnGhostHouse = true;
		
		mode = "SCATTER";
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
			chaseY = pacman.getY();
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
		} else {
			if (i >= redGhost.length) {
				i = 0;
			}
			return redGhost[(int) i];
		}
	}
}
