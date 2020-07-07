package pacmanGame;

public class TargettingSystem {
	private void test() {
		int currentX = 105;
		int currentY = 223;
		
		boolean upperTileIsAvailable = Board.getSingleton().isTileAvailable(currentX, currentY - 1);
		boolean rightTileIsAvailable = Board.getSingleton().isTileAvailable(currentX + 1, currentY);
		boolean downTileIsAvailable = Board.getSingleton().isTileAvailable(currentX, currentY + 1);
		boolean leftTileIsAvailable = Board.getSingleton().isTileAvailable(currentX - 1, currentY);
		
		int availablePathsCount = 0;
		if (upperTileIsAvailable) availablePathsCount |= 0x01;
		if (rightTileIsAvailable) availablePathsCount |= 0x02;
		if (downTileIsAvailable) availablePathsCount |= 0x04;
		if (leftTileIsAvailable) availablePathsCount |= 0x08;
		
		
		if (Board.getSingleton().isTileAvailable(5, 6)) {
			// true logic
		} else {
			// false logic
		}
	}
}
