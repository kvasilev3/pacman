package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Pacman {

	private Image yellowMan;

	public Pacman() {
		ImageIcon pacmanImage = new ImageIcon("Pacman/src/resources/pacman.png");
		yellowMan = pacmanImage.getImage();
	}
	
	public Image getImage() {
		return yellowMan;
	}
}
