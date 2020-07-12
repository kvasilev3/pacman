package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Clyde extends Ghost {

	private String mode = "SCATTER";
	private int i = 0;
	private int scatterX = 0;
	private int scatterY = 155;
	private int chaseX = 0;
	private int chaseY = 0;
	private Image[] yellowGhost = {
			new ImageIcon("Pacman/src/resources/clyde_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/clyde_2.png").getImage()
	};
	
	public Clyde() {
		//Starting Position
		x = 7; //83
		y = 147; //71
		direction = Direction.Up;
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
			if (i >= yellowGhost.length) {
				i = 0;
			}
		}
		return yellowGhost[i];
	}
}