package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Pinky extends Ghost {

	private int i = 0;
	private Image[] pinkGhost = {
			new ImageIcon("Pacman/src/resources/pinky_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/pinky_2.png").getImage()
	};
	
	public Pinky() {
		init();
	}
	
	@Override
	protected void init() {
		//Starting Position
		x = 7; //70
		y = 7; //73
		direction = Direction.Down;
		
		scatterX = 10;
		scatterY = -20;
		mode = "CHASE";
	}
	
	@Override
	public int getTargetX(Sprite pacman, Sprite blinky) {
		if (getMode() == "SCATTER") {
			return scatterX;
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
			if (i >= pinkGhost.length) {
				i = 0;
			}
		}
		return pinkGhost[i];
	}
}