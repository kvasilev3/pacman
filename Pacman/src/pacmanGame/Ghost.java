package pacmanGame;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;


public class Ghost extends Sprite {
	private Random random = new Random();
	protected String mode = "SCATTER";
	protected int scatterX = 0;
	protected int scatterY = 0;
	protected int chaseX = 0;
	protected int chaseY = 0;
	protected int ghostHouseX = 70;
	protected int ghostHouseY = 75;
	protected double i = 0;
	private int j = 0;
	protected Image[] frightenedGhost = {
			new ImageIcon("Pacman/src/resources/escaping_ghost_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/escaping_ghost_2.png").getImage(),
			new ImageIcon("Pacman/src/resources/escaping_ghost_white_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/escaping_ghost_white_2.png").getImage()
			};
	private TargettingSystem ts = new TargettingSystem();

	public Ghost() {
		inGhostHouseY = 75;
		inGhostHouseX = 70;
	}
	
	private Direction[] getPossibleDirections(int x, int y, Direction currentDirection) {
		ArrayList<Direction> possibleDirections = new ArrayList<>();
		boolean canEnterGhostHouse = Board.getSingleton().isTileInGhostHouse(x, y) || getMode() == "EATEN";
		if (getMode() == "GHOST_HOUSE") {
			if (Board.getSingleton().isTileWalkable(x, y - 1, true)) {
				possibleDirections.add(Direction.Up);
			}
			/*if (Board.getSingleton().isTileWalkable(x - 1, y, true)) {
				possibleDirections.add(Direction.Left);
			}
			if (Board.getSingleton().isTileWalkable(x + 1, y, true)) {
				possibleDirections.add(Direction.Right);
			}*/
			if (Board.getSingleton().isTileWalkable(x, y + 1, true)) {
				possibleDirections.add(Direction.Down);
			}
		} else {
			if (currentDirection == Direction.Up) {
				if (Board.getSingleton().isTileWalkable(x, y - 1, canEnterGhostHouse)) {
					possibleDirections.add(Direction.Up);
				}
				if (Board.getSingleton().isTileWalkable(x - 1, y, canEnterGhostHouse)) {
					possibleDirections.add(Direction.Left);
				}
				if (Board.getSingleton().isTileWalkable(x + 1, y, canEnterGhostHouse)) {
					possibleDirections.add(Direction.Right);
				}
			} else if (currentDirection == Direction.Left) {
				if (Board.getSingleton().isTileWalkable(x - 1, y, canEnterGhostHouse)) {
					possibleDirections.add(Direction.Left);
				}
				if (Board.getSingleton().isTileWalkable(x, y + 1, canEnterGhostHouse)) {
					possibleDirections.add(Direction.Down);
				}
				if (Board.getSingleton().isTileWalkable(x, y - 1, canEnterGhostHouse)) {
					if (x >= 55 && x <= 84 && y == 57) {
					} else if (x >= 55 && x <= 84 && y == 117) {
					} else {
						possibleDirections.add(Direction.Up);
					}
				}
			} else if (currentDirection == Direction.Down) {
				if (Board.getSingleton().isTileWalkable(x - 1, y, canEnterGhostHouse)) {
					possibleDirections.add(Direction.Left);
				}
				if (Board.getSingleton().isTileWalkable(x, y + 1, canEnterGhostHouse)) {
					possibleDirections.add(Direction.Down);
				}
				if (Board.getSingleton().isTileWalkable(x + 1, y, canEnterGhostHouse)) {
					possibleDirections.add(Direction.Right);
				}
			} else if (currentDirection == Direction.Right) {
				if (Board.getSingleton().isTileWalkable(x, y - 1, canEnterGhostHouse)) {
					if (x >= 55 && x <= 84 && y == 57) {
					} else if (x >= 55 && x <= 84 && y == 117) {
					} else {
						possibleDirections.add(Direction.Up);
					}
				}
				if (Board.getSingleton().isTileWalkable(x, y + 1, canEnterGhostHouse)) {
					possibleDirections.add(Direction.Down);
				}
				if (Board.getSingleton().isTileWalkable(x + 1, y, canEnterGhostHouse)) {
					possibleDirections.add(Direction.Right);
				}
			}
		}
		Direction result[] = new Direction[possibleDirections.size()];
		return possibleDirections.toArray(result);
	}

	@Override
	protected void move(Sprite pacman, Sprite blinky) {
		Direction possibleDirections[] = getPossibleDirections(x, y, direction);

		if (getMode() == "GHOST_HOUSE") {
			if (x == getTargetX(pacman, blinky) && y == getTargetY(pacman, blinky)) {
				setMode(getSecondaryMode());
				this.direction = this.direction.oppositeDirection();
				System.out.println("Set to secondary mode");
			}
			if (x == 70 && y == 69) {
				this.direction = this.direction.oppositeDirection();
			}
		}
		
		if (possibleDirections.length < 1) {
			return;
		} else if (possibleDirections.length == 1) {
			if (getMode() == "GHOST_HOUSE") {
				if (x == getTargetX(pacman, blinky) && y == getTargetY(pacman, blinky)) {
					setMode(getSecondaryMode());
					System.out.println("Set to secondary mode");
				}
			}
			
			if (getMode() == "EATEN") {
				if (x == 70 && y == 57) {
					this.x += Direction.Down.getDeltaX();
					this.y += Direction.Down.getDeltaY();
					this.direction = Direction.Down;
				}
			}
			
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
				Direction minPathScatter = ts.findMinPath(x, y, getTargetX(pacman, blinky), getTargetY(pacman, blinky), direction, possibleDirections);
				this.x += minPathScatter.getDeltaX();
				this.y += minPathScatter.getDeltaY();
				this.direction = minPathScatter;
				
			} else if (getMode() == "CHASE") {
				Direction minPathChase = ts.findMinPath(x, y, getTargetX(pacman, blinky), getTargetY(pacman, blinky), direction, possibleDirections);
				this.x += minPathChase.getDeltaX();
				this.y += minPathChase.getDeltaY();
				this.direction = minPathChase;
				
			} else if (getMode() == "EATEN") {
				if (x == getTargetX(pacman, blinky) && y == getTargetY(pacman, blinky)) {
					setMode(getSecondaryMode());
					this.x += this.direction.oppositeDirection().getDeltaX();
					this.y += this.direction.oppositeDirection().getDeltaY();
					this.direction = this.direction.oppositeDirection();
				} else {
					Direction minPathEaten = ts.findMinPath(x, y, getTargetX(pacman, blinky), getTargetY(pacman, blinky), direction, possibleDirections);
					this.x += minPathEaten.getDeltaX();
					this.y += minPathEaten.getDeltaY();
					this.direction = minPathEaten;
				}
				
			} else if (getMode() == "GHOST_HOUSE") {
				if (x == getTargetX(pacman, blinky) && y == getTargetY(pacman, blinky)) {
					setMode(getSecondaryMode());
					System.out.println("Set to secondary mode");
				} else {
					Direction minPathGH = ts.findMinPath(x, y, getTargetX(pacman, blinky), getTargetY(pacman, blinky), direction, possibleDirections);
					this.x += minPathGH.getDeltaX();
					this.y += minPathGH.getDeltaY();
					this.direction = minPathGH;
				}
				
			} else {
				//TODO: EXCEPTION: No Mode
			}
		}
	}
	
	@Override
	public String getMode() {
		return mode;
	}
	
	@Override
	public void setMode(String givenMode) {
		mode = givenMode;
	}
	
	@Override
	public int getTargetX(Sprite pacman, Sprite blinky) {
		if (getMode() == "SCATTER") {
			return scatterX;
		} else {
			return chaseX;
		}
	}
	
	@Override
	public int getTargetY(Sprite pacman, Sprite blinky) {
		if (getMode() == "SCATTER") {
			return scatterY;
		} else {
			return chaseY;
		}
	}
	
	public Image getFrightenedImage(double frightenedModeStart, double timeCount) {
		if (i >= frightenedGhost.length) {
			i = 0;
			j++;
			if (j >= frightenedGhost.length / 2) j = 0;
		}
		if (timeCount - frightenedModeStart >= 4000) {
			if (j == 0) {
				return frightenedGhost[((int) i) % 2];
			} else {
				return frightenedGhost[(((int) i) % 2) + 2];
			}
		} else {
			return frightenedGhost[(int) (i / 2)];
		}
	}
}