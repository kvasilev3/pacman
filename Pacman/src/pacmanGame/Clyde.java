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
		//Starting Position
		x = 7; //83
		y = 147; //71
		direction = Direction.Up;
		
		scatterX = 0;
		scatterY = 155;
		mode = "CHASE";
	}
	
	@Override
	public int getChaseX(Sprite pacman, Sprite blinky) {
		double distanceToPacman = Math.sqrt(Math.pow(pacman.getX() - getX(), 2) + Math.pow(pacman.getY() - getY(), 2));
		if (distanceToPacman <= 8) {
			chaseX = scatterX;
		} else {
			chaseX = pacman.getX();
		}
		return chaseX;
	}
	
	@Override
	public int getChaseY(Sprite pacman, Sprite blinky) {
		double distanceToPacman = Math.sqrt(Math.pow(pacman.getX() - getX(), 2) + Math.pow(pacman.getY() - getY(), 2));
		if (distanceToPacman <= 8) {
			chaseY = scatterY;
		} else {
			chaseY = pacman.getY();
		}
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
		}else {
			if (i >= yellowGhost.length) {
				i = 0;
			}
		}
		return yellowGhost[i];
	}
}