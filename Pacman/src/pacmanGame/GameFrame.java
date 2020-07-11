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

		Board board = Board.getSingleton();
		add(board);
		//board.setFocusable(true);
		
        setTitle("Pacman original 2.0");
        setSize(392, 457);
        
        ImageIcon myAppImage = new ImageIcon("Pacman/src/resources/pacman_desktop.png");
        setIconImage(myAppImage.getImage());
        
        setLocationRelativeTo(null);
        setResizable(true); //Temporary (Will be switched to true when main game is done)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
	
	public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
        	GameFrame gameFrame = new GameFrame();
            gameFrame.setVisible(true);
        });
    }
}
