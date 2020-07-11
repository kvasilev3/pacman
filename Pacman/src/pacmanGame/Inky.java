package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Inky extends Ghost {
	
	public Inky() {
		//Starting Position
		x = 132; //55
		y = 147; //71
		direction = Direction.Up;
	}
	
	private Image[] cyanGhost = {
			new ImageIcon("Pacman/src/resources/inky_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/inky_2.png").getImage()
			};
	private int i = 0;

	@Override
	public Image getImage() {
		i++;
		if (i >= cyanGhost.length) {
			i = 0;
		}
		return cyanGhost[i];
	}
}
