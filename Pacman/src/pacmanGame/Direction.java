package pacmanGame;

public enum Direction {
	Up(0, -1), Left(-1, 0), Down(0, 1), Right(1, 0);

	private final int deltaX;
	private final int deltaY;

	private Direction(int deltaX, int deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public int getDeltaX() {
		return this.deltaX;
	}

	public int getDeltaY() {
		return this.deltaY;
	}
}
