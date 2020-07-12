package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Inky extends Ghost {
	
	private int i = 0;
	private Image[] cyanGhost = {
			new ImageIcon("Pacman/src/resources/inky_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/inky_2.png").getImage()
			};
	
	public Inky() {
		//Starting Position
		x = 132; //55
		y = 147; //71
		direction = Direction.Up;
		
		scatterX = 135;
		scatterY = 155;
		mode = "CHASE";
	}
	
	@Override
	public int getChaseX(Sprite pacman, Sprite blinky) {
		int blinkyX = blinky.getX();
		if (pacman.direction == Direction.Up) {
			chaseX = pacman.getX() - 2;
		} else if (pacman.direction == Direction.Left) {
			chaseX = pacman.getX() - 2;
		} else if (pacman.direction == Direction.Down) {
			chaseX = pacman.getX();
		} else if (pacman.direction == Direction.Right) {
			chaseX = pacman.getX() + 2;
		}
		
		int distanceToBlinky = chaseX - blinkyX;
		chaseX = chaseX + distanceToBlinky;
		
		return chaseX;
	}
	
	@Override
	public int getChaseY(Sprite pacman, Sprite blinky) {
		int blinkyY = blinky.getY();
		if (pacman.direction == Direction.Up) {
			chaseY = pacman.getY() - 2;
		} else if (pacman.direction == Direction.Left) {
			chaseY = pacman.getY();
		} else if (pacman.direction == Direction.Down) {
			chaseY = pacman.getY() + 2;
		} else if (pacman.direction == Direction.Right) {
			chaseY = pacman.getY() ;
		}

		int distanceToBlinky = chaseX - blinkyY;
		chaseY = chaseY + distanceToBlinky;
		
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
			if (i >= cyanGhost.length) {
				i = 0;
			}
		}
		return cyanGhost[i];
	}
}
