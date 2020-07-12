package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Pinky extends Ghost {

	private String mode = "SCATTER";
	private int i = 0;
	private int scatterX = 10;
	private int scatterY = -20;
	private int chaseX = 0;
	private int chaseY = 0;
	private Image[] pinkGhost = {
			new ImageIcon("Pacman/src/resources/pinky_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/pinky_2.png").getImage()
	};
	
	public Pinky() {
		//Starting Position
		x = 7; //70
		y = 7; //73
		direction = Direction.Down;
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
	public int getScatterX() {
		return scatterX;
	}
	
	@Override
	public int getScatterY() {
		return scatterY;
	}
	
	@Override
	public int getChaseX() {
		return chaseX;
	}
	
	@Override
	public int getChaseY() {
		return chaseY;
	}
	
	@Override
	public Image getImage() {
		i++;
		if (mode == "FRIGHTENED") {
			if (i >= frightenedGhost.length) {
				i = 0;
			}
			return frightenedGhost[i];
		} else {
			if (i >= pinkGhost.length) {
				i = 0;
			}
		}
		return pinkGhost[i];
	}
}