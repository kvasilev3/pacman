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
	private final int GRID_WIDTH = 28*5; // The width of the original game was 28 tiles. We've decided to make it 5 times bigger.
	private final int GRID_HEIGHT = 31*5; // The height of the original game was 31 tiles. We've decided to make it 5 times bigger. 
	private int tiles[][];
	private final int TILE_IS_NOT_WALKABLE = 0;
	private final int TILE_IS_WALKABLE = 1;
	private final int TILE_HAS_PELLET = 2;
	private final int TILE_HAS_POWER_PELLET = 3;
	private final int TILE_PELLET_EATEN = 4;
	
	private static Board singleton = new Board();
	
	private Blinky blinky = new Blinky();
	private Inky inky = new Inky();
	private Pacman pacman = new Pacman();
	
	private Board() {
		initTiles();
		
		ImageIcon ii = new ImageIcon("Pacman/src/resources/maze.png");
        background = ii.getImage();
        
		timer = new Timer(DELAY, this);
        timer.start();
	}
	
	private void initTiles() {
		tiles = new int[GRID_WIDTH][GRID_HEIGHT];
		
		// Sample values
		tiles[2][2] = TILE_HAS_POWER_PELLET;
		tiles[2][3] = TILE_IS_WALKABLE;
		tiles[2][4] = TILE_IS_WALKABLE;
		tiles[2][5] = TILE_IS_WALKABLE;
		tiles[2][6] = TILE_IS_WALKABLE;
		
		tiles[2][7] = TILE_HAS_PELLET;
		tiles[3][7] = TILE_IS_WALKABLE;
		tiles[4][7] = TILE_IS_WALKABLE;
		tiles[5][7] = TILE_IS_WALKABLE;
		tiles[6][7] = TILE_IS_WALKABLE;
		
		tiles[7][2] = TILE_HAS_PELLET;
		tiles[7][3] = TILE_IS_WALKABLE;
		tiles[7][4] = TILE_IS_WALKABLE;
		tiles[7][5] = TILE_IS_WALKABLE;
		tiles[7][6] = TILE_IS_WALKABLE;
		tiles[7][7] = TILE_HAS_PELLET;
	}
	
	public static Board getSingleton() {
		return singleton;
	}
	
	public boolean isTileWalkable(int x, int y) {
		if (x < 0 || x >= GRID_WIDTH)
			return false;
		if (y < 0 || y >= GRID_HEIGHT)
			return false;
		return tiles[x][y] > TILE_IS_NOT_WALKABLE;
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, this);
        
        //Coordinates are starting positions of the sprite
        g2d.drawImage(blinky.getImage(), 310, 175, this); 
        g2d.drawImage(inky.getImage(), 285, 215, this);
        g2d.drawImage(pacman.getImage(), 310, 340, this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
