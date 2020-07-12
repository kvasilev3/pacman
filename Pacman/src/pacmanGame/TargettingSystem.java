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
	
	private double distanceToTarget(int currentX, int currentY, int targetX, int targetY) {
		return Math.sqrt(Math.pow(targetX - currentX, 2) + Math.pow(targetY - (currentY), 2));
	}
	
	public Direction findMinPath(int currentX, int currentY, int targetX, int targetY, Direction direction) {
		Direction directions[] = new Direction[4];
		directions[0] = Direction.Up;
		directions[1] = Direction.Right;
		directions[2] = Direction.Down;
		directions[3] = Direction.Left;
		
		Direction oppositeDirection = direction.oppositeDirection();
		
		Direction directionWithMinDistance = Direction.Up;
		double minDistance = 1000000;
		
		for (int i=0; i<directions.length; i++) {
			if (directions[i] == oppositeDirection) continue;
			if (Board.getSingleton().isTileWalkable(currentX + directions[i].getDeltaX(), currentY + directions[i].getDeltaY())) {
				double currentDistance = distanceToTarget(currentX + directions[i].getDeltaX(), currentY + directions[i].getDeltaY(), targetX, targetY);
				if (minDistance > currentDistance) {
					minDistance = currentDistance;
					directionWithMinDistance = directions[i];
				}
			}
		}
		
		return directionWithMinDistance;
	}
}
