package pacmanGame;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Pacman extends Sprite {
	
	private Direction nextDirection = Direction.Down;
	
	public Pacman() {
		//Starting Position
		x = 70;
		y = 117;
		direction = Direction.Left;
	}

	private Image[] yellowManLeft = {
			new ImageIcon("Pacman/src/resources/pacman.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_left_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_left_2.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_left_1.png").getImage()
			};
	private Image[] yellowManRight = {
			new ImageIcon("Pacman/src/resources/pacman.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_right_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_right_2.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_right_1.png").getImage()
			};
	private Image[] yellowManUp = {
			new ImageIcon("Pacman/src/resources/pacman.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_up_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_up_2.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_up_1.png").getImage()
			};
	private Image[] yellowManDown = {
			new ImageIcon("Pacman/src/resources/pacman.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_down_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_down_2.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_down_1.png").getImage()
			};
	private int i = 0;

	public Image getImage() {
		i++;
		if (i >= yellowManLeft.length) {
			i = 0;
		}
		if (direction == Direction.Up) {
			return yellowManUp[i];
		}
		else if (direction == Direction.Down) {
			return yellowManDown[i];
		}
		else if (direction == Direction.Right) {
			return yellowManRight[i];
		}
		else if (direction == Direction.Left) {
			return yellowManLeft[i];
		}
		else {
			return yellowManLeft[i];
		}
		
	}
	
	public void setNextDirection(Direction direction) {
		nextDirection = direction;
	}
	
	public Direction[] getPossibleDirections(int x, int y) {
		ArrayList<Direction> possibleDirections = new ArrayList<>();
		
		if (Board.getSingleton().isTileWalkable(x, y - 1)) {
			possibleDirections.add(Direction.Up);
		}
		if (Board.getSingleton().isTileWalkable(x - 1, y)) {
			possibleDirections.add(Direction.Left);
		}
		if (Board.getSingleton().isTileWalkable(x + 1, y)) {
			possibleDirections.add(Direction.Right);
		}
		if (Board.getSingleton().isTileWalkable(x, y + 1)) {
			possibleDirections.add(Direction.Down);
		}
		Direction result[] = new Direction[possibleDirections.size()];
		return possibleDirections.toArray(result);
	}
	
	@Override
	protected void move() {
		// Check for intersection
		// Check for walkable tiles
		Direction[] possibleDirections = getPossibleDirections(x, y);
		for (int i = 0; i < possibleDirections.length; i++) {
			if(nextDirection == possibleDirections[i]) {
				direction = nextDirection;
				this.x += direction.getDeltaX();
				this.y += direction.getDeltaY();
			}
			else if (direction == possibleDirections[i]) {
				this.x += direction.getDeltaX();
				this.y += direction.getDeltaY();
			}
		}
		
		
	}
}
