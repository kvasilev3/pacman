package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Pacman {

	private Image[] yellowMan = {
			new ImageIcon("Pacman/src/resources/pacman.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_right_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_right_2.png").getImage(),
			new ImageIcon("Pacman/src/resources/pacman_right_1.png").getImage()
			};
	private int i = 0;

	public Image getImage() {
		i++;
		if (i >= yellowMan.length) {
			i = 0;
		}
		return yellowMan[i];
	}
}
