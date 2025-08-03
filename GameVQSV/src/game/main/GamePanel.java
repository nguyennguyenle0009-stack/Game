package game.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final int originalTitleSize = 16;
	final int scale = 3;
	
	public final int titleSize = originalTitleSize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = titleSize * maxScreenCol;
	final int screenHeight = titleSize * maxScreenRow;
	
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread thread;
	Player player = new Player(this, keyH);
	
	//set player default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
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

	@Override
//	public void run() {
//		while(thread != null) {
//			//System.out.println("Running");
//			
//			//long currentTime = System.nanoTime();
//			//System.out.println("Curremt time: " + currentTime);
//			
//			double drawInterval = 1000000000/FPS; //0.16666 SECONDS
//			double nextDrawTime = System.nanoTime() + drawInterval;
//			
//			//Update: update is information such as character positions
//			update();
//			
//			//Draw: draw the screen with the update information
//			repaint();
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				Thread.sleep((long) remainingTime);
//				nextDrawTime += drawInterval;
//			} catch(InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//		} 
//		
//	}
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
		player.draw(g2);
		g2.dispose();
	}
}
