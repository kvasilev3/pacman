package pacmanGame;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame() {
		
		initUI();
	}
	
	private void initUI() {

		add(Board.getSingleton());
		
        setTitle("Pacman original 2.0");
        setSize(646, 509);
        
        ImageIcon myAppImage = new ImageIcon("Pacman/src/resources/pacman_desktop.png");
        setIconImage(myAppImage.getImage());
        
        setLocationRelativeTo(null);
        setResizable(false); //Temporary (Will be switched to true when main game is done)
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
	
	public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
        	GameFrame gameFrame = new GameFrame();
            gameFrame.setVisible(true);
        });
    }
}
