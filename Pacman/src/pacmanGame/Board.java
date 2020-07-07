package pacmanGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
	
	private Timer timer;
	private final int FRAMES_PER_SECOND = 30;
	private final int DELAY = 1000 / FRAMES_PER_SECOND; // ms
	private Image background;
	private int tiles[][] = new int[140][155];
	
	private static Board singleton = new Board();
	
	private Blinky blinky = new Blinky();
	
	private Board() {
		ImageIcon ii = new ImageIcon("Pacman/src/resources/maze.png");
        background = ii.getImage();
        
		timer = new Timer(DELAY, this);
        timer.start();
	}
	
	public static Board getSingleton() {
		return singleton;
	}
	
	public boolean isTileAvailable(int x, int y) {
		return false;
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, this);
        
        g2d.drawImage(blinky.getImage(), 10, 10, this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
