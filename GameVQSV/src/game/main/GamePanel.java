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
	
	public int tileSize = originalTitleSize * scale;
	public int maxScreenCol = 16;
	public int maxScreenRow = 12;
	public int screenWidth = tileSize * maxScreenCol;
	public int screenHeight = tileSize * maxScreenRow;
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	int FPS = 60;
	
	TileManager tile = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	Thread thread;
	public Player player = new Player(this, keyH);

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void zoomInOut(int i) {
		int oldWorldWidth = tileSize * maxWorldCol; //2400
		tileSize += i;
		int newWorldWidth = tileSize * maxWorldCol; //2300
		
		player.speed = (double)worldWidth/600;
		
		double mutiplier = (double)newWorldWidth/oldWorldWidth;
		
//		System.out.println("tileSize: " + tileSize);
//		System.out.println("worldWidth: " + newWorldWidth);
//		System.out.println("playerWorldX: " + player.worldX);
//		System.out.println("speed: " + player.speed);
		
		double newPlayerWorldX = player.worldX * mutiplier;
		double newPlayerWorldY = player.worldY * mutiplier;
		
		player.worldX = newPlayerWorldX;
		player.worldY = newPlayerWorldY;
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
