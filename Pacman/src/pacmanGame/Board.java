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
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Board extends JPanel {

	private Timer pacmanTimer;
	private Timer ghostsTimer;
	private Timer redrawTimer;
	private final int PACMAN_MOVEMENTS_PER_SECOND = 18;
	private final int PACMAN_DELAY = 1000 / PACMAN_MOVEMENTS_PER_SECOND; // ms
	private final int GHOSTS_MOVEMENTS_PER_SECOND = 13;
	private final int GHOSTS_DELAY = 1000 / GHOSTS_MOVEMENTS_PER_SECOND; // ms
	private final int REDRAW_PER_SECOND = 30;
	private final int REDRAW_DELAY = 1000 / REDRAW_PER_SECOND; // ms
	private Image background;
	private Image pellet;
	private Image powerPellet;

	private final int GRID_WIDTH = 28*5; // The width of the original game was 28 tiles. We've decided to make it 5 times bigger.
	private final int GRID_HEIGHT = 31*5; // The height of the original game was 31 tiles. We've decided to make it 5 times bigger. 

	private final int TILE_IS_NOT_WALKABLE = 0;
	private final int TILE_IS_WALKABLE = 1;
	private final int TILE_HAS_PELLET = 2;
	private final int TILE_HAS_POWER_PELLET = 3;
	private final int TILE_NO_PELLET = 4;
	
	private ImageIcon eyesUp = new ImageIcon("Pacman/src/resources/eyes_up.png");
	private ImageIcon eyesLeft = new ImageIcon("Pacman/src/resources/eyes_left.png");
	private ImageIcon eyesDown = new ImageIcon("Pacman/src/resources/eyes_down.png");
	private ImageIcon eyesRight = new ImageIcon("Pacman/src/resources/eyes_right.png");
	
	private boolean debuggerMode = true;
	private ImageIcon blinkyTarget = new ImageIcon("Pacman/src/resources/blinky_target.png");
	private ImageIcon inkyTarget = new ImageIcon("Pacman/src/resources/inky_target.png");
	private ImageIcon pinkyTarget = new ImageIcon("Pacman/src/resources/pinky_target.png");
	private ImageIcon clydeTarget = new ImageIcon("Pacman/src/resources/clyde_target.png");
	
	private int tiles[][] = new int[GRID_WIDTH][GRID_HEIGHT];
	private int score = 0;

	private static Board singleton = new Board();
	
	private Pacman pacman = new Pacman();
	private Sprite ghosts[] = {
			new Blinky(),
			new Inky(),
			new Pinky(),
			new Clyde()
	};

	private Board() {
		
		mapKeys();
		
		initTiles();
		
		ImageIcon ii = new ImageIcon("Pacman/src/resources/maze.png");
		background = ii.getImage();
		
		ii = new ImageIcon("Pacman/src/resources/pellet.png");
		pellet = ii.getImage();
		
		ii = new ImageIcon("Pacman/src/resources/power_pellet.png");
		powerPellet = ii.getImage();

		pacmanTimer = new Timer(PACMAN_DELAY, new PacmanMoveListener());
		pacmanTimer.start();
		
		ghostsTimer = new Timer(GHOSTS_DELAY, new GhostsMoveListener());
		ghostsTimer.start();
		
		redrawTimer = new Timer(REDRAW_DELAY, new RedrawListener());
		redrawTimer.start();
	}
	
	private void mapKeys() {
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("UP"), "moveUp");
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
		getActionMap().put("moveUp", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	pacman.setNextDirection(Direction.Up);
		    }
		});
		getActionMap().put("moveLeft", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	pacman.setNextDirection(Direction.Left);
		    }
		});
		getActionMap().put("moveDown", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	pacman.setNextDirection(Direction.Down);
		    }
		});
		getActionMap().put("moveRight", new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	pacman.setNextDirection(Direction.Right);
		    }
		});
	}
	
	private void initTiles() {
		final int BLUE = 0xFF0000FF;
		final int GREEN = 0xFF00FF00;
		final int RED = 0xFFFF0000;
		
		tiles = new int[GRID_HEIGHT][GRID_WIDTH];
		
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
		
		for (int y=0; y<GRID_HEIGHT; y++) {
			for (int x=0; x<GRID_WIDTH; x++) {
				if (tiles[y][x] == TILE_HAS_PELLET) {
					g2d.drawImage(pellet, convertX(x)+11, convertY(y)+11, this);
				} else if (tiles[y][x] == TILE_HAS_POWER_PELLET) {
					g2d.drawImage(powerPellet, convertX(x)+5, convertY(y)+5, this);
				}
			}
		}
		
		for (int i = 0; i < ghosts.length; i++) {
			
			g2d.drawImage(ghosts[i].getImage(), convertX(ghosts[i].getX()), convertY(ghosts[i].getY()), this);
			
			if (ghosts[i].getMode() != "FRIGHTENED") {
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
		}
		
		g2d.drawImage(pacman.getImage(), convertX(pacman.getX()), convertY(pacman.getY()), this);
		
		if (debuggerMode == true) {
			g2d.drawImage(blinkyTarget.getImage(), convertX(ghosts[0].getTargetX(pacman, ghosts[0])), convertY(ghosts[0].getTargetY(pacman, ghosts[0])), this);
			g2d.drawImage(inkyTarget.getImage(), convertX(ghosts[1].getTargetX(pacman, ghosts[0])), convertY(ghosts[1].getTargetY(pacman, ghosts[0])), this);
			g2d.drawImage(pinkyTarget.getImage(), convertX(ghosts[2].getTargetX(pacman, ghosts[0])), convertY(ghosts[2].getTargetY(pacman, ghosts[0])), this);
			g2d.drawImage(clydeTarget.getImage(), convertX(ghosts[3].getTargetX(pacman, ghosts[0])), convertY(ghosts[3].getTargetY(pacman, ghosts[0])), this);
		}
	}
	
	private int convertX(int x) {
		return (int) (2.7571429 * (x-3));
	}

	private int convertY(int y) {
		return (int) (2.76129 * (y-3));
	}

	private class PacmanMoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			pacman.move(pacman, ghosts[0]);
			
			if (pacman.getX() == 0 && pacman.getY() == 72 && pacman.direction == Direction.Left) {
				pacman.setX(138);
				pacman.setY(72);
			} else if (pacman.getX() == 139 && pacman.getY() == 72 && pacman.direction == Direction.Right) {
				pacman.setX(1);
				pacman.setY(72);
			}
			if (tiles[pacman.getY()][pacman.getX()] == TILE_HAS_PELLET) {
				tiles[pacman.getY()][pacman.getX()] = TILE_NO_PELLET;
				score += 10;
				System.out.println("Score: " + score);
			} else if (tiles[pacman.getY()][pacman.getX()] == TILE_HAS_POWER_PELLET) {
				tiles[pacman.getY()][pacman.getX()] = TILE_NO_PELLET;
				score += 50;
				for (int i = 0; i < ghosts.length; i++) {
					ghosts[i].setMode("FRIGHTENED");
					ghosts[i].direction = ghosts[i].direction.oppositeDirection();
				}
				System.out.println("Score: " + score);
			}
		}
	}
	
	private class GhostsMoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < ghosts.length; i++) {
				ghosts[i].move(pacman, ghosts[0]);
				
				if (ghosts[i].getX() == 0 && ghosts[i].getY() == 72 && ghosts[i].direction == Direction.Left) {
					ghosts[i].setX(138);
					ghosts[i].setY(72);
				} else if (ghosts[i].getX() == 139 && ghosts[i].getY() == 72 && ghosts[i].direction == Direction.Right) {
					ghosts[i].setX(1);
					ghosts[i].setY(72);
				}
				if (Math.abs(ghosts[i].getX() - pacman.getX()) < 2 && Math.abs(ghosts[i].getY() - pacman.getY()) < 2) {
					if (ghosts[i].getMode() == "FRIGHTENED") {
						ghosts[i].setMode("EATEN");
						score += 200;
					} else if (ghosts[i].getMode() == "EATEN") {
					} else {
						System.out.println("Pacman was eaten");
					}
				}
			}
		}
	}
	
	private class RedrawListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}