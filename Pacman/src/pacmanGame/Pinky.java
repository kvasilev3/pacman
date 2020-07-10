package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Pinky extends Ghost {

	public Pinky() {
		//Starting Position
		x = 7;
		y = 7;
		direction = Direction.Down;
	}
	
	private Image[] pinkGhost = {
			new ImageIcon("Pacman/src/resources/pinky_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/pinky_2.png").getImage()
	};
	
	private int i = 0;
	@Override
	public Image getImage() {
		i++;
		if (i >= pinkGhost.length) {
			i = 0;
		}
		return pinkGhost[i];
	}
}