package pacmanGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
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
	private final int FRAME = REDRAW_DELAY;
	private Image background;
	private Image[] winBackground = {
			new ImageIcon("Pacman/src/resources/maze_win_1.png").getImage(),
			new ImageIcon("Pacman/src/resources/maze_win_2.png").getImage()
	};
	private Image pellet;
	private Image powerPellet;
	private boolean pacmanDead = false;
	private boolean levelComplete = false;
	private double winCount = 0;
	private double timeCount = 0;
	private double frightenedModeStart = 0;
	private double frightenedModeEnd = 0;
	private double levelCompleteTimer = Double.MAX_VALUE;
	private double modeStart = 0;
	private int modeCount = 0;

	private final int GRID_WIDTH = 28*5; // The width of the original game was 28 tiles. We've decided to make it 5 times bigger.
	private final int GRID_HEIGHT = 31*5; // The height of the original game was 31 tiles. We've decided to make it 5 times bigger. 

	private final int TILE_IS_NOT_WALKABLE = 0;
	private final int TILE_IS_WALKABLE = 1;
	private final int TILE_HAS_PELLET = 2;
	private final int TILE_HAS_POWER_PELLET = 3;
	private final int TILE_NO_PELLET = 4;
	private final int TILE_IS_WALKABLE_GHOST_HOUSE = 5;
	
	private ImageIcon eyesUp = new ImageIcon("Pacman/src/resources/eyes_up.png");
	private ImageIcon eyesLeft = new ImageIcon("Pacman/src/resources/eyes_left.png");
	private ImageIcon eyesDown = new ImageIcon("Pacman/src/resources/eyes_down.png");
	private ImageIcon eyesRight = new ImageIcon("Pacman/src/resources/eyes_right.png");
	
	private ImageIcon pacmanLives = new ImageIcon("Pacman/src/resources/pacman_right_1.png");
	
	private boolean debuggerMode = true;
	private ImageIcon grid = new ImageIcon("Pacman/src/resources/tiles_grid.png");
	private ImageIcon blinkyTarget = new ImageIcon("Pacman/src/resources/blinky_target.png");
	private ImageIcon inkyTarget = new ImageIcon("Pacman/src/resources/inky_target.png");
	private ImageIcon pinkyTarget = new ImageIcon("Pacman/src/resources/pinky_target.png");
	private ImageIcon clydeTarget = new ImageIcon("Pacman/src/resources/clyde_target.png");
	
	
	private int tiles[][] = new int[GRID_WIDTH][GRID_HEIGHT];
	private double[] modeSwitches = {
		//	MINUTES			+ SECONDS	 + FRAMES (1/30 OF SEC)
			(0 * 60 * 1000) + (7 * 1000) + (0 * FRAME),
			(0 * 60 * 1000) + (20 * 1000) + (0 * FRAME),
			(0 * 60 * 1000) + (7 * 1000) + (0 * FRAME),
			(0 * 60 * 1000) + (20 * 1000) + (0 * FRAME),
			(0 * 60 * 1000) + (5 * 1000) + (0 * FRAME),
			(17 * 60 * 1000) + (13 * 1000) + (14 * FRAME),
			(0 * 60 * 1000) + (0 * 1000) + (1 * FRAME)
	};
	private int score = 0;
	public int lives = 3;
	

	private static Board singleton = new Board();
	
	private Pacman pacman = new Pacman();
	private Sprite ghosts[] = {
			new Blinky(),
			new Inky(),
			new Pinky(),
			new Clyde()
	};

	private Board() {
		
		Dimension d = new Dimension(392, 518);
		setPreferredSize(d);
		
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
		final int YELLOW = 0xFFFFFF00;
		
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
				} else if (color == YELLOW) {
					tiles[y][x] = TILE_IS_WALKABLE_GHOST_HOUSE;
				}
			}
		}
	}
	
	public static Board getSingleton() {
		return singleton;
	}
	
	public boolean isTileWalkable(int x, int y, boolean canWalkIntoGhostHouse) {
		if (x < 0 || x >= GRID_WIDTH)
			return false;
		if (y < 0 || y >= GRID_HEIGHT)
			return false;
		if (canWalkIntoGhostHouse) {
			return tiles[y][x] > TILE_IS_NOT_WALKABLE;
		} else {
			return tiles[y][x] > TILE_IS_NOT_WALKABLE && tiles[y][x] < TILE_IS_WALKABLE_GHOST_HOUSE;
		}
	}
	
	public boolean isTileWalkable(int x, int y) {
		return isTileWalkable(x, y, false);
	}
	
	public boolean isTileInGhostHouse(int x, int y) {
		return tiles[y][x] == TILE_IS_WALKABLE_GHOST_HOUSE;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		timeCount += REDRAW_DELAY;
		int p = 0;

		Graphics2D g2d = (Graphics2D) g;
		setBackground(Color.BLACK);
		if (levelComplete) {
			if (winCount <= 1.5) {
				winCount += 0.1;
				g2d.drawImage(winBackground[(int) winCount], 0, 0, this);
			}
			else {
				winCount = 0;
				g2d.drawImage(winBackground[1], 0, 0, this);
			}
		} else {
			g2d.drawImage(background, 0, 0, this);
		}
		if (debuggerMode) {
			g2d.drawImage(grid.getImage(), 0, 0, this);
			p -= 230;
		}
		
		for (int y=0; y<GRID_HEIGHT; y++) {
			for (int x=0; x<GRID_WIDTH; x++) {
				if (tiles[y][x] == TILE_HAS_PELLET) {
					g2d.drawImage(pellet, convertX(x)+11, convertY(y)+11, this);
					p++;
				} else if (tiles[y][x] == TILE_HAS_POWER_PELLET) {
					g2d.drawImage(powerPellet, convertX(x)+5, convertY(y)+5, this);
					p++;
				}
			}
		}
		
		if (p == 0) {
			if (!levelComplete) {
				levelComplete = true;
				levelCompleteTimer = timeCount + 2300;
			}
		}
		
		if (levelComplete && timeCount >= levelCompleteTimer) {
			levelCompleteTimer = Double.MAX_VALUE;
			levelComplete = false;
			timeCount = 0;
			initTiles();
			resetSprites();
		}
		
		if (!pacmanDead && !levelComplete) {
			for (int i = 0; i < ghosts.length; i++) {
				
				g2d.drawImage(ghosts[i].getImage(frightenedModeStart, timeCount), convertX(ghosts[i].getX()), convertY(ghosts[i].getY()), this);
				
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
						//TODO: EXCEPTION: ghosts[i] has no direction
					}
				}
			}
		}
		if (!levelComplete) {
			g2d.drawImage(pacman.getImage(), convertX(pacman.getX()), convertY(pacman.getY()), this);
			if (pacman.isDeathDone()) {
				useNextLife();
			}
		}
		
		if (debuggerMode && !pacmanDead && !levelComplete) {
			g2d.drawImage(blinkyTarget.getImage(), convertX(ghosts[0].getTargetX(pacman, ghosts[0])), convertY(ghosts[0].getTargetY(pacman, ghosts[0])), this);
			g2d.drawImage(inkyTarget.getImage(), convertX(ghosts[1].getTargetX(pacman, ghosts[0])), convertY(ghosts[1].getTargetY(pacman, ghosts[0])), this);
			g2d.drawImage(pinkyTarget.getImage(), convertX(ghosts[2].getTargetX(pacman, ghosts[0])), convertY(ghosts[2].getTargetY(pacman, ghosts[0])), this);
			g2d.drawImage(clydeTarget.getImage(), convertX(ghosts[3].getTargetX(pacman, ghosts[0])), convertY(ghosts[3].getTargetY(pacman, ghosts[0])), this);
		}
		
		for (int i = 0; i < lives; i++) {
			g2d.drawImage(pacmanLives.getImage(), (i * 30) + 5, convertY(32 * 5), this);
		}
		String pacmanScore = Integer.toString(score);
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial", Font.BOLD, 15));
		g2d.drawString("SCORE:", (int) (getWidth() - 120), 15);
		g2d.drawString(pacmanScore, (int) (getWidth() - 100), getCenteredTextCoordinates(g2d, pacmanScore)[1]);
		
		g2d.setFont(new Font("Arial", Font.BOLD, 25));
		g2d.drawString("PACMAN", 60, getCenteredTextCoordinates(g2d, pacmanScore)[1]);
	}
	
	private void resetSprites() {
		ghosts[0] = new Blinky();
		ghosts[1] = new Inky();
		ghosts[2] = new Pinky();
		ghosts[3] = new Clyde();
		pacman = new Pacman();
	}

	private int[] getCenteredTextCoordinates(Graphics2D g2d, String text) {
		FontRenderContext context = g2d.getFontRenderContext();
		Font font = new Font("Arial", Font.BOLD, 15);
		TextLayout txt = new TextLayout(text, font, context);

		Rectangle2D bounds = txt.getBounds();
		int x = (int) ((getWidth() - (int) bounds.getWidth()) / 2);
		int y = (int) convertX(10);
		y += txt.getAscent() - txt.getDescent();
		
		int[] coords = {
				x,
				y
		};
		
		return coords;
	}
	
	private int convertX(int x) {
		return (int) (2.8 * x - 10);
	}

	private int convertY(int y) {
		return (int) (2.8 * y + 32);
	}

	private class PacmanMoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (pacmanDead || levelComplete) {
				return;
			}
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
			} else if (tiles[pacman.getY()][pacman.getX()] == TILE_HAS_POWER_PELLET) {
				tiles[pacman.getY()][pacman.getX()] = TILE_NO_PELLET;
				score += 50;
				frightenedModeStart = timeCount;
				frightenedModeEnd = frightenedModeStart + 8000;
				for (int i = 0; i < ghosts.length; i++) {
					if (ghosts[i].getMode() == "SCATTER" || ghosts[i].getMode() == "CHASE") {
						ghosts[i].setMode("FRIGHTENED");
						ghosts[i].direction = ghosts[i].direction.oppositeDirection();
					}
				}
			}
		}
	}
	
	private class GhostsMoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (timeCount + REDRAW_DELAY >= frightenedModeEnd && frightenedModeEnd != 0) {
				for (int i = 0; i < ghosts.length; i++) {
					if (ghosts[i].getMode() == "FRIGHTENED") {
						ghosts[i].setMode(ghosts[i].getSecondaryMode());
						ghosts[i].direction = ghosts[i].direction.oppositeDirection();
					}
				}
				frightenedModeEnd = 0;
			}
			
			if (timeCount + REDRAW_DELAY >= 1000) ghosts[2].inGhostHouseY = 75;
			if (timeCount + REDRAW_DELAY >= 2000) ghosts[1].inGhostHouseY = 75;
			if (timeCount + REDRAW_DELAY >= 3000) ghosts[3].inGhostHouseY = 75;
			
			if (modeCount >= modeSwitches.length) {
				ghosts[0].setSecondaryMode("CHASE");
				
			} else {
				if (timeCount - modeStart >= modeSwitches[modeCount]) {
					modeCount++;
					if (modeCount % 2 == 0) {
						for (int i = 0; i < ghosts.length; i++) {
							ghosts[i].setSecondaryMode("SCATTER");
							modeStart = timeCount;
							if (ghosts[i].getMode() == "CHASE") {
								ghosts[i].setMode(ghosts[i].secondaryMode);
								ghosts[i].direction = ghosts[i].direction.oppositeDirection();
							}
						}
					} else {
						for (int i = 0; i < ghosts.length; i++) {
							ghosts[i].setSecondaryMode("CHASE");
							modeStart = timeCount;
							if (ghosts[i].getMode() == "SCATTER") {
								ghosts[i].setMode(ghosts[i].secondaryMode);
								ghosts[i].direction = ghosts[i].direction.oppositeDirection();
							}
						}
					}
				}
			}
			
			if (pacmanDead || levelComplete) {
				return;
			}
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
						pacmanDead = true;
						pacman.setPacmansLife(pacmanDead);
						lives  -= 1;
						modeCount = 0;
						modeStart = 0;
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

	public void useNextLife() {
		if (lives > 0) {
			pacmanDead = false;
			pacman.setPacmansLife(pacmanDead);
			pacman.resetSprite();
			for (int i = 0; i < ghosts.length; i++) {
				ghosts[i].resetSprite();
			}
		} else {
			return;
		}
		
	}
}