package pacmanGame;

public class TargettingSystem {	
	private double distanceToTarget(int currentX, int currentY, int targetX, int targetY) {
		return Math.sqrt(Math.pow(targetX - currentX, 2) + Math.pow(targetY - (currentY), 2));
	}
	
	public Direction findMinPath(int currentX, int currentY, int targetX, int targetY, Direction direction, Direction[] possibleDirections) {
		Direction oppositeDirection = direction.oppositeDirection();
		
		Direction directionWithMinDistance = Direction.Up;
		double minDistance = 1000000;
		
		for (int i=0; i<possibleDirections.length; i++) {
			if (possibleDirections[i] == oppositeDirection) continue;
			double currentDistance = distanceToTarget(currentX + possibleDirections[i].getDeltaX(), currentY + possibleDirections[i].getDeltaY(), targetX, targetY);
			if (minDistance > currentDistance) {
				minDistance = currentDistance;
				directionWithMinDistance = possibleDirections[i];
			}
		}
		
		return directionWithMinDistance;
	}
}
