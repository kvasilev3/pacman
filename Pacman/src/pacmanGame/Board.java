package pacmanGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private Timer timer;
	private final int FRAMES_PER_SECOND = 10;
	private final int DELAY = 1000 / FRAMES_PER_SECOND; // ms
	private Image background;

	private final int GRID_WIDTH = 28*5; // The width of the original game was 28 tiles. We've decided to make it 5 times bigger.
	private final int GRID_HEIGHT = 31*5; // The height of the original game was 31 tiles. We've decided to make it 5 times bigger. 

	private final int TILE_IS_NOT_WALKABLE = 0;
	private final int TILE_IS_WALKABLE = 1;
	private final int TILE_HAS_PELLET = 2;
	private final int TILE_HAS_POWER_PELLET = 3;
	private final int TILE_PELLET_EATEN = 4;
	
	private int tiles[][] = new int[GRID_WIDTH][GRID_HEIGHT];

	private static Board singleton = new Board();

	private Blinky blinky = new Blinky();
	private Inky inky = new Inky();
	private Pinky pinky = new Pinky();
	private Clyde clyde = new Clyde();
	private Pacman pacman = new Pacman();

	private Board() {
		initTiles();
		
		ImageIcon ii = new ImageIcon("Pacman/src/resources/maze.png");
		background = ii.getImage();

		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	private void initTiles() {
		final int BLUE = 0xFF0000FF;
		final int GREEN = 0xFF00FF00;
		final int RED = 0xFFFF0000;
		
		tiles = new int[GRID_HEIGHT][GRID_WIDTH];
		
		/*// Sample values
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
		tiles[7][7] = TILE_HAS_PELLET;*/
		
		File imageFile = new File("Pacman/src/resources/tiles.png");
		BufferedImage image = null;
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		if (image.getWidth() != GRID_WIDTH) {
			return;
		}
		if (image.getHeight() != GRID_HEIGHT) {
			return;
		}
		
		for (int y = 0; y < GRID_HEIGHT; y++) {
			for (int x = 0; x < GRID_WIDTH; x++) {
				int color = image.getRGB(x, y);
				if (color == BLUE) {
					tiles[y][x] = TILE_IS_WALKABLE;
				} else if (color == GREEN) {
					tiles[y][x] = TILE_HAS_PELLET;
				} else if (color == RED) {
					tiles[y][x] = TILE_HAS_POWER_PELLET;
				}
			}
		}
	}
	
	public static Board getSingleton() {
		return singleton;
	}
	
	public boolean isTileWalkable(int x, int y) {
		if (x < 0 || x >= GRID_WIDTH)
			return false;
		if (y < 0 || y >= GRID_HEIGHT)
			return false;
		return tiles[y][x] > TILE_IS_NOT_WALKABLE;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, this);

		// Coordinates are starting positions of the sprite
		g2d.drawImage(blinky.getImage(), 182, 149, this);
		g2d.drawImage(inky.getImage(), 157, 188, this);
		g2d.drawImage(pinky.getImage(), 182, 188, this);
		g2d.drawImage(clyde.getImage(), 207, 188, this);
		g2d.drawImage(pacman.getImage(), 182, 312, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
