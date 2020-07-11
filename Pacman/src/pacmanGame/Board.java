package pacmanGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private Timer timer;
	private final int MOVEMENTS_PER_SECOND = 11;
	private final int DELAY = 1000 / MOVEMENTS_PER_SECOND; // ms
	private Image background;

	private final int GRID_WIDTH = 28*5; // The width of the original game was 28 tiles. We've decided to make it 5 times bigger.
	private final int GRID_HEIGHT = 31*5; // The height of the original game was 31 tiles. We've decided to make it 5 times bigger. 

	private final int TILE_IS_NOT_WALKABLE = 0;
	private final int TILE_IS_WALKABLE = 1;
	private final int TILE_HAS_PELLET = 2;
	private final int TILE_HAS_POWER_PELLET = 3;
	private final int TILE_PELLET_EATEN = 4;
	
	private ImageIcon eyesUp = new ImageIcon("Pacman/src/resources/eyes_up.png");
	private ImageIcon eyesLeft = new ImageIcon("Pacman/src/resources/eyes_left.png");
	private ImageIcon eyesDown = new ImageIcon("Pacman/src/resources/eyes_down.png");
	private ImageIcon eyesRight = new ImageIcon("Pacman/src/resources/eyes_right.png");
	
	private int tiles[][] = new int[GRID_WIDTH][GRID_HEIGHT];

	private static Board singleton = new Board();
	
	private Pacman pacman = new Pacman();
	private Sprite ghosts[] = {
			new Blinky(),
			new Inky(),
			new Pinky(),
			new Clyde()
	};

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
		
		for (int i = 0; i < ghosts.length; i++) {
			ghosts[i].move();
			
			if (ghosts[i].getX() == 0 && ghosts[i].getY() == 72 && ghosts[i].direction == Direction.Left) {
				ghosts[i].setX(138);
				ghosts[i].setY(72);
			} else if (ghosts[i].getX() == 139 && ghosts[i].getY() == 72 && ghosts[i].direction == Direction.Right) {
				ghosts[i].setX(1);
				ghosts[i].setY(72);
			}
			
			g2d.drawImage(ghosts[i].getImage(), convertX(ghosts[i].getX()), convertY(ghosts[i].getY()), this);
			
			if (ghosts[i].direction == Direction.Up) {
				g2d.drawImage(eyesUp.getImage(), convertX(ghosts[i].getX()), convertY(ghosts[i].getY()), this);
			} else if (ghosts[i].direction == Direction.Left) {
				g2d.drawImage(eyesLeft.getImage(), convertX(ghosts[i].getX()), convertY(ghosts[i].getY()), this);
			} else if (ghosts[i].direction == Direction.Down) {
				g2d.drawImage(eyesDown.getImage(), convertX(ghosts[i].getX()), convertY(ghosts[i].getY()), this);
			} else if (ghosts[i].direction == Direction.Right) {
				g2d.drawImage(eyesRight.getImage(), convertX(ghosts[i].getX()), convertY(ghosts[i].getY()), this);
			} else {
				System.out.println(ghosts[i] + "has no direction!");
			}
		}
		// Coordinates are starting positions of the sprite
		g2d.drawImage(pacman.getImage(), 182, 312, this);
	}
	
	private int convertX(int x) {
		return (int) (2.7571429 * (x-3));
	}

	private int convertY(int y) {
		return (int) (2.76129 * (y-3));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}