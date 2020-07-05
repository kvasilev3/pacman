package pacmanGame;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame() {
		
		initUI();
	}
	
	private void initUI() {

		add(new Board());
		
        setTitle("Pacman original 2.0");
        setSize(646, 509);
        
        setLocationRelativeTo(null);
        setResizable(false); //Temporary (Will be switched to true when main game is done)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
        	GameFrame gameFrame = new GameFrame();
            gameFrame.setVisible(true);
        });
    }
}
