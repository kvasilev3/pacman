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
		init();
	}
	
	@Override
	protected void init() {
		//Starting Position
		x = 59;
		y = 75;
		direction = Direction.Down;
		
		scatterX = 135;
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
