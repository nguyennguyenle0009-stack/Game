package game.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import game.entity.Entity;
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
	
	//WORLD SETTING
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	//FPS
	int FPS = 60;
	
	//SYSTEM
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Thread thread;
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	
	//ENTITY AND OBJECT
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[10];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	//GAME STATE
	public int gameState;
	public int titleState;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setUpGame() {
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		playMusic(0);
		stopMusic();
		gameState = titleState;
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
		if(gameState == playState) {
			//PLAYER
			player.update();
			//NPC
			for(int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].update();
				}
			}
			
			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					monster[i].update();
				}
			}
		}
		if(gameState == pauseState) {
			//nothing
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//Debug
		long drawStart = 0;
		//Dể kiểm tra thời gian
		if(keyH.checkDrwaTime == true) {
			drawStart = System.nanoTime();
		}
		
		//TITLE SCREEN
		if(gameState == titleState) {
			ui.draw(g2);
		}
		
		//OTHERS
		else {
			
			//TILE
			tileM.draw(g2);
			
			//ADD ENTITIES TO THE LIST
			entityList.add(player);
			
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			
			//SORT
			Collections.sort(entityList, new Comparator<Entity>() {
				
				public int compare(Entity e1, Entity e2) {
					
					int result = Integer.compare(e1.worldY, e2.worldX);
					return result;
				}
			});
			
			//DRAW ENTITIES
			for (int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			
			//EMPTY ENTITY LIST
			for(int i = 0; i < entityList.size(); i++) {
				entityList.remove(i);
			}
			
			//ui			
			ui.draw(g2);
		}
		
		//debug
		if(keyH.checkDrwaTime == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw time: " + passed, 10, 400);
			System.out.println("Draw time: " + passed);
		}
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}





















