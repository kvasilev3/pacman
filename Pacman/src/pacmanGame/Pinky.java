package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Pinky extends Ghost {

	private Image[] pinkGhost = {
			new ImageIcon("Pacman/src/resources/pinky_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/pinky_2.png").getImage()
	};
	
	private int i = 0;
	public Image getImage() {
		i++;
		if (i >= pinkGhost.length) {
			i = 0;
		}
		return pinkGhost[i];
	}
	
	public static int getX() {
		return 182;
	}
	
	public static int getY() {
		return 188;
	}
}