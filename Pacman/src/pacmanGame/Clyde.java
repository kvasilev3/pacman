package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Clyde extends Ghost {

	private Image[] yellowGhost = {
			new ImageIcon("Pacman/src/resources/clyde_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/clyde_2.png").getImage()
	};
	private int i = 0;
	
	public Image getImage() {
		i++;
		if (i >= yellowGhost.length) {
			i = 0;
		}
		return yellowGhost[i];
	}
}