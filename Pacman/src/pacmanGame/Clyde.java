package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Clyde extends Ghost {
	
	private int i = 0;
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
		
		scatterX = 0;
		scatterY = 155;
		mode = "GHOSTHOUSE";
	}
	
	@Override
	public int getTargetX(Sprite pacman, Sprite blinky) {
		if (getMode() == "SCATTER") {
			return scatterX;
		} else if (getMode() == "EATEN") {
			return ghostHouseX;
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
	public Image getImage() {
		i++;
		if (mode == "FRIGHTENED") {
			if (i >= frightenedGhost.length) {
				i = 0;
			}
			return frightenedGhost[i];
		} else if (mode == "EATEN") {
			return null;
		}else {
			if (i >= yellowGhost.length) {
				i = 0;
			}
		}
		return yellowGhost[i];
	}
}