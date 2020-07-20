package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Blinky extends Ghost {

	private double i = 0;
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
		
		scatterX = 130;
		scatterY = 0;
		mode = "SCATTER";
	}

	@Override
	public int getTargetX(Sprite pacman, Sprite blinky) {
		if (getMode() == "SCATTER") {
			return scatterX;
		} else if (getMode() == "EATEN") {
			return ghostHouseX;
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
		} else {
			chaseY = pacman.getY();
			return chaseY;
		}
	}
	
	@Override
	public Image getImage() {
		i += 0.5;
		if (mode == "FRIGHTENED") {
			if (i >= frightenedGhost.length) {
				i = 0;
			}
			return frightenedGhost[(int) i];
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
