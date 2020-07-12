package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Inky extends Ghost {
	
	private String mode = "SCATTER";
	private int i = 0;
	private int scatterX = 135;
	private int scatterY = 155;
	private int chaseX = 0;
	private int chaseY = 0;
	private Image[] cyanGhost = {
			new ImageIcon("Pacman/src/resources/inky_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/inky_2.png").getImage()
			};
	
	public Inky() {
		//Starting Position
		x = 132; //55
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
			if (i >= cyanGhost.length) {
				i = 0;
			}
		}
		return cyanGhost[i];
	}
}
