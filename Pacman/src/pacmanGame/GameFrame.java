package pacmanGame;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame() {
		
		initUI();
	}
	
	private ImageIcon createImageIcon(String path) {
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
	
	private void initUI() {

		Board board = Board.getSingleton();
		add(board);
		
        setTitle("Pacman original 2.0");
        
        ImageIcon myAppImage = createImageIcon("/resources/pacman_desktop.png");
        setIconImage(myAppImage.getImage());
        
        setLocationRelativeTo(null);
        setResizable(false);
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack();
       
    }
	
	public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
        	GameFrame gameFrame = new GameFrame();
            gameFrame.setVisible(true);
        });
    }
}