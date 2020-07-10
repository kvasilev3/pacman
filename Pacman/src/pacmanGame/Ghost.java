package pacmanGame;

import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Sprite {
	private Random random = new Random();

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
			int index = random.nextInt(possibleDirections.length);
			this.x += possibleDirections[index].getDeltaX();
			this.y += possibleDirections[index].getDeltaY();
			this.direction = possibleDirections[index];
		}
	}
}
