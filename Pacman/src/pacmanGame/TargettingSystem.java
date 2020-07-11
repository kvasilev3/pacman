package pacmanGame;

public class TargettingSystem {
	
	private void decideNextStop(int currentX, int currentY, Direction direction, int tatgetX, int targetY) {

		boolean upperTileIsAvailable = Board.getSingleton().isTileWalkable(currentX, currentY - 1);
		boolean rightTileIsAvailable = Board.getSingleton().isTileWalkable(currentX + 1, currentY);
		boolean downTileIsAvailable = Board.getSingleton().isTileWalkable(currentX, currentY + 1);
		boolean leftTileIsAvailable = Board.getSingleton().isTileWalkable(currentX - 1, currentY);
		
		int a = 0x20;
		
		int availablePathsFlags = 0;
		if (direction != Direction.Up && downTileIsAvailable) availablePathsFlags += 1;
		if (direction != Direction.Right && leftTileIsAvailable) availablePathsFlags += 2;
		if (direction != Direction.Down && upperTileIsAvailable) availablePathsFlags += 4;
		if (direction != Direction.Left && rightTileIsAvailable) availablePathsFlags += 8;
	
		if (availablePathsFlags == 1)	{
			currentY -= 1;
		}
		else if (availablePathsFlags == 2)	{
			currentX += 1;
		}
		else if (availablePathsFlags == 4)	{
			currentY += 1;
		}
		else if (availablePathsFlags == 8)	{
			currentX -= 1;
		}

		else {
			// Choose random path
		}
		
	}

	private double distanceFromUpper(int currentX, int currentY, int targetX, int targetY) {
		return Math.sqrt(Math.pow(targetX - currentX, 2) + Math.pow(targetY - (currentY - 1), 2));
	}
	private double distanceFromRight(int currentX, int currentY, int targetX, int targetY) {
		return Math.sqrt(Math.pow(targetX - (currentX + 1), 2) + Math.pow(targetY - currentY, 2));
	}
	private double distanceFromDown(int currentX, int currentY, int targetX, int targetY) {
		return Math.sqrt(Math.pow(targetX - currentX, 2) + Math.pow(targetY - (currentY + 1), 2));
	}
	private double distanceFromLeft(int currentX, int currentY, int targetX, int targetY) {
		return Math.sqrt(Math.pow(targetX - (currentX - 1), 2) + Math.pow(targetY - currentY, 2));
	}
}
