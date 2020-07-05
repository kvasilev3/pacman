package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Blinky extends Ghost {
	
	private Image redGhost;
	
	public Blinky() {
		ImageIcon blinkyImage = new ImageIcon("Pacman/src/resources/maze.png");
		redGhost = blinkyImage.getImage();
	}
}
