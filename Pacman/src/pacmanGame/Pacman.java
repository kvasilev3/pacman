package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Pacman extends Sprite {
	
	private Direction currentDirection = Direction.Left;
	private Direction nextDirection = Direction.Down;
	
	public Pacman() {
		//Starting Position
		x = 70;
		y = 117;
		direction = Direction.Left;
	}

	private Image[] yellowMan = {
			new ImageIcon("Pacman/src/resources/pacman.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_right_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_right_2.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_right_1.png").getImage()
			};
	private int i = 0;

	public Image getImage() {
		i++;
		if (i >= yellowMan.length) {
			i = 0;
		}
		return yellowMan[i];
	}
	
	public void setNextDirection(Direction direction) {
		nextDirection = direction;
	}
	
	@Override
	protected void move() {
		// Check for intersection
		// Check for walkable tiles
		this.x += nextDirection.getDeltaX();
		this.y += nextDirection.getDeltaY();
	}
}
