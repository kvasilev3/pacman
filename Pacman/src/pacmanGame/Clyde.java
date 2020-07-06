package pacmanGame;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Clyde extends Ghost {

	private Image yellowGhost;
	
	public Clyde() {
		ImageIcon clydeImage = new ImageIcon("Pacman/src/resources/clyde_1.png");
		yellowGhost = clydeImage.getImage();
	}
}
