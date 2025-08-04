package game.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.main.GamePanel;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	
	public void draw (Graphics g2, GamePanel gp) {
		
		double screentX = worldX - gp.player.worldX + gp.player.screentX;
		double screentY = worldY - gp.player.worldY + gp.player.screentY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screentX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screentX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screentY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screentY) {
			g2.drawImage(image, (int)screentX, (int)screentY, gp.tileSize, gp.tileSize, null);
		}
	}
}
