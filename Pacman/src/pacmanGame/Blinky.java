package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Blinky extends Ghost {
	
	public Blinky() {
		//Starting Position
		x = 70;
		y = 57;
		direction = Direction.Left;
	}

	private Image[] redGhost = {
			new ImageIcon("Pacman/src/resources/blinky_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/blinky_2.png").getImage()
			};
	private int i = 0;

	@Override
	public Image getImage() {
		i++;
		if (i >= redGhost.length) {
			i = 0;
		}
		return redGhost[i];
	}
}
