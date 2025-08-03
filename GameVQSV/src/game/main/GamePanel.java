package game.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import game.entity.Player;
import game.tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final int originalTitleSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTitleSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	int FPS = 60;
	
	TileManager tile = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread thread;
	Player player = new Player(this, keyH);

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		double drawInterval = 1000000000/FPS; //0.16666 SECONDS
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime ;
		long time = 0;
		int drawCount =0;
		while(thread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) /drawInterval;
			time += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(time >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				time = 0;
			}

		}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		tile.draw(g2);
		player.draw(g2);
		g2.dispose();
	}
}
