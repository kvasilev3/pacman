package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Inky extends Ghost {
	
	private Image[] cyanGhost = {
			new ImageIcon("Pacman/src/resources/inky_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/inky_2.png").getImage()
			};
	private int i = 0;

	public Image getImage() {
		i++;
		if (i >= cyanGhost.length) {
			i = 0;
		}
		return cyanGhost[i];
	}
}
