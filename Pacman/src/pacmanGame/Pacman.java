package pacmanGame;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Pacman extends Sprite {
	
	private Direction nextDirection = Direction.Left;
	private boolean pacmanDead = false;
	private double i = 0;
	
	public Pacman() {
		init();
	}
	
	@Override
	protected void init() {
		//Starting Position
		x = 70;
		y = 117;
		direction = Direction.Left;
	}
	
	public void setPacmansLife(boolean pacmanDead) {
		this.pacmanDead = pacmanDead;
		i = 0;
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
	private Image[] pacmanDie = {
			new ImageIcon("Pacman/src/resources/pacman_die_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_die_2.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_die_3.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_die_4.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_die_5.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_die_6.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_die_7.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_die_8.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_die_9.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_die_10.png").getImage(),
			
	};
	
	public boolean isDeathDone() {
		return i >= 9;
	}

	public Image getImage() {
		if (!pacmanDead) {
			i += 0.1;
			if (i >= yellowManLeft.length) {
				i = 0;
			}
			if (direction == Direction.Up) {
				return yellowManUp[(int) i];
			}
			else if (direction == Direction.Down) {
				return yellowManDown[(int) i];
			}
			else if (direction == Direction.Right) {
				return yellowManRight[(int) i];
			}
			else if (direction == Direction.Left) {
				return yellowManLeft[(int) i];
			}
			else {
				return yellowManLeft[(int) i];
			}
			
		}
		else {
			if (i <= 9) {
				i += 0.1;
				return pacmanDie[(int) i];
			}
			else {
				return pacmanDie[9];
			}
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
	protected void move(Sprite pacman, Sprite blinky) {
		// Check for intersection
		// Check for walkable tiles
		Direction[] possibleDirections = getPossibleDirections(x, y);
		for (int i = 0; i < possibleDirections.length; i++) {
			if(nextDirection == possibleDirections[i]) {
				direction = nextDirection;
			}
		}
		for (int i = 0; i < possibleDirections.length; i++) {
			if (direction == possibleDirections[i]) {
				this.x += direction.getDeltaX();
				this.y += direction.getDeltaY();
			}
		}
	}
}
