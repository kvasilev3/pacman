package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Blinky extends Ghost {

	private Image[] redGhost = {
			new ImageIcon("Pacman/src/resources/blinky_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/blinky_2.png").getImage()
			};
	private int i = 0;

	public Image getImage() {
		i++;
		if (i >= redGhost.length) {
			i = 0;
		}
		return redGhost[i];
	}
}
