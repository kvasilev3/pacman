package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Blinky extends Ghost {

	private int i = 0;
	private Image[] redGhost = {
			new ImageIcon("Pacman/src/resources/blinky_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/blinky_2.png").getImage()
			};

	public Blinky() {
		// Starting Position
		x = 70;
		y = 57;
		direction = Direction.Left;
		
		scatterX = 125;
		scatterY = -20;
		mode = "CHASE";
	}

	@Override
	public int getChaseX(Sprite pacman, Sprite blinky) {
		chaseX = pacman.getX();
		return chaseX;
	}
	
	@Override
	public int getChaseY(Sprite pacman, Sprite blinky) {
		chaseY = pacman.getY();
		return chaseY;
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
		} else {
			if (i >= redGhost.length) {
				i = 0;
			}
			return redGhost[i];
		}
	}
}
