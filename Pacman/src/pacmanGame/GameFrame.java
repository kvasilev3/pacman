package pacmanGame;

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
		
        setTitle("Pacman original 2.0");
        
        ImageIcon myAppImage = new ImageIcon("Pacman/src/resources/pacman_desktop.png");
        setIconImage(myAppImage.getImage());
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack();
       
    }
	
	public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
        	GameFrame gameFrame = new GameFrame();
            gameFrame.setVisible(true);
        });
    }
}