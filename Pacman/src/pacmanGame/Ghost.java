package pacmanGame;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;


public class Ghost extends Sprite {
	private Random random = new Random();
	protected Image[] frightenedGhost = {
			new ImageIcon("Pacman/src/resources/escaping_ghost_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/escaping_ghost_2.png").getImage()
			};
	private TargettingSystem ts = new TargettingSystem();

	public Direction[] getPossibleDirections(int x, int y, Direction currentDirection) {
		ArrayList<Direction> possibleDirections = new ArrayList<>();
		if (currentDirection == Direction.Up) {
			if (Board.getSingleton().isTileWalkable(x, y - 1)) {
				possibleDirections.add(Direction.Up);
			}
			if (Board.getSingleton().isTileWalkable(x - 1, y)) {
				possibleDirections.add(Direction.Left);
			}
			if (Board.getSingleton().isTileWalkable(x + 1, y)) {
				possibleDirections.add(Direction.Right);
			}
		} else if (currentDirection == Direction.Left) {
			if (Board.getSingleton().isTileWalkable(x - 1, y)) {
				possibleDirections.add(Direction.Left);
			}
			if (Board.getSingleton().isTileWalkable(x, y + 1)) {
				possibleDirections.add(Direction.Down);
			}
			if (Board.getSingleton().isTileWalkable(x, y - 1)) {
				if (x >= 55 && x <= 84 && y == 57) {
				} else if (x >= 55 && x <= 84 && y == 117) {
				} else {
					possibleDirections.add(Direction.Up);
				}
			}
		} else if (currentDirection == Direction.Down) {
			if (Board.getSingleton().isTileWalkable(x - 1, y)) {
				possibleDirections.add(Direction.Left);
			}
			if (Board.getSingleton().isTileWalkable(x, y + 1)) {
				possibleDirections.add(Direction.Down);
			}
			if (Board.getSingleton().isTileWalkable(x + 1, y)) {
				possibleDirections.add(Direction.Right);
			}
		} else if (currentDirection == Direction.Right) {
			if (Board.getSingleton().isTileWalkable(x, y - 1)) {
				if (x >= 55 && x <= 84 && y == 57) {
				} else if (x >= 55 && x <= 84 && y == 117) {
				} else {
					possibleDirections.add(Direction.Up);
				}
			}
			if (Board.getSingleton().isTileWalkable(x, y + 1)) {
				possibleDirections.add(Direction.Down);
			}
			if (Board.getSingleton().isTileWalkable(x + 1, y)) {
				possibleDirections.add(Direction.Right);
			}
		}
		Direction result[] = new Direction[possibleDirections.size()];
		return possibleDirections.toArray(result);
	}

	@Override
	protected void move() {
		Direction possibleDirections[] = getPossibleDirections(x, y, direction);

		if (possibleDirections.length < 1) {
			return;
		} else if (possibleDirections.length == 1) {
			this.x += possibleDirections[0].getDeltaX();
			this.y += possibleDirections[0].getDeltaY();
			this.direction = possibleDirections[0];
		} else {
			if (getMode() == "FRIGHTENED") {
				int index = random.nextInt(possibleDirections.length);
				this.x += possibleDirections[index].getDeltaX();
				this.y += possibleDirections[index].getDeltaY();
				this.direction = possibleDirections[index];
				
			} else if (getMode() == "SCATTER") {
				Direction minPathScatter = ts.findMinPath(x, y, getScatterX(), getScatterY(), direction);
				this.x += minPathScatter.getDeltaX();
				this.y += minPathScatter.getDeltaY();
				this.direction = minPathScatter;
				
			} else if (getMode() == "CHASE") {
				this.direction = ts.findMinPath(x, y, getChaseX(), getChaseY(), direction);
				
			} else if (getMode() == "EATEN") {
				if (x == 70 && y == 57) {
					setMode("GHOSTHOUSE");
				}
				this.direction = ts.findMinPath(x, y, 70, 57, direction);
				
			} else if (getMode() == "GHOSTHOUSE") {
				
				
			} else {
				//TODO: Exception - No Mode
			}
		}
	}
}