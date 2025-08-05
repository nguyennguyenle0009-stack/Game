package game.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.main.GamePanel;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAeaDefaultX = 0;
	public int solidAeaDefaultY = 0;
	
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
