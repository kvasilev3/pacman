package pacmanGame;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame() {
		
		initUI();
	}
	
	private void initUI() {

        setTitle("Pacman 2.0");
        setSize(640, 480);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
        	GameFrame gameFrame = new GameFrame();
            gameFrame.setVisible(true);
        });
    }
}
