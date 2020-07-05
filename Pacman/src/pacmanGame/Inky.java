package pacmanGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Inky extends Ghost {
	
private Image cyanGhost;
	
	public Inky() {
		ImageIcon inkyImage = new ImageIcon("Pacman/src/resources/inky_1.png");
		cyanGhost = inkyImage.getImage();
	}
}
