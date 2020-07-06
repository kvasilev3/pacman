package pacmanGame;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Pinky extends Ghost {

	private Image pinkGhost;
	
	public Pinky() {
		ImageIcon pinkyImage = new ImageIcon("Pacman/src/resources/pinky_1.png");
		pinkGhost = pinkyImage.getImage();
	}
}
