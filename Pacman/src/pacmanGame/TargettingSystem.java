package pacmanGame;

public class TargettingSystem {
	private void test() {
		int currentX = 105; //sample values 
		int currentY = 223; //sample values 
		
		boolean upperTileIsAvailable = Board.getSingleton().isTileWalkable(currentX, currentY - 1);
		boolean rightTileIsAvailable = Board.getSingleton().isTileWalkable(currentX + 1, currentY);
		boolean downTileIsAvailable = Board.getSingleton().isTileWalkable(currentX, currentY + 1);
		boolean leftTileIsAvailable = Board.getSingleton().isTileWalkable(currentX - 1, currentY);
		
		int availablePathsCount = 0;
		if (upperTileIsAvailable) availablePathsCount += 1;
		if (rightTileIsAvailable) availablePathsCount += 2;
		if (downTileIsAvailable) availablePathsCount += 4;
		if (leftTileIsAvailable) availablePathsCount += 8;
		
//		if (upperTileIsAvailable) availablePathsCount |= 0x01;
//		if (rightTileIsAvailable) availablePathsCount |= 0x02;
//		if (downTileIsAvailable) availablePathsCount |= 0x04;
//		if (leftTileIsAvailable) availablePathsCount |= 0x08;
		
		
	
		if (availablePathsCount == 1)	{
			currentY -= 1;
		}
		else if (availablePathsCount == 2)	{
			currentX += 1;
		}
		else if (availablePathsCount == 4)	{
			currentY += 1;
		}
		else if (availablePathsCount == 8)	{
			currentX -= 1;
		}
		//^^ for only 1 available tile
		
		
		else if (availablePathsCount == 3)	{
			
		}
		else if (availablePathsCount == 5)	{
			
		}
		else if (availablePathsCount == 9)	{
			
		}
		else if (availablePathsCount == 6)	{
			
		}
		else if (availablePathsCount == 10){
			
		}
		else if (availablePathsCount == 12)	{
			
		}
		//^^ for 2 available tiles
		
		
		else if (availablePathsCount == 7)	{
			
		}
		else if (availablePathsCount == 11)	{
			
		}
		else if (availablePathsCount == 13)	{
			
		}
		else if (availablePathsCount == 14)	{
			
		}
		//^^ for 3 available tiles
		
		
		else if (availablePathsCount == 15){
			
		}
		//^^ if all 4 tiles available
		

		
		
//		private double distancefromUpper(currentX, currentY, int targetX, int targetY) {
//			
//		}
		
		
//		if (Board.getSingleton().isTileWalkable(currentX, currentY)) {
//			// true logic
//		} else {
//			// false logic
//		}
		
	}
}
