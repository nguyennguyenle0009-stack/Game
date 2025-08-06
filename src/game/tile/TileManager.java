package game.tile;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import game.main.GamePanel;
import game.main.UtilityTool;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[50];
		mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
		getTileManager();
		loadMap("/resources/maps/worldV2.txt");
	}
	
	public void getTileManager() {	
		
		// PLACEHOLDER
		setUp(1, "grass00", false);
		setUp(2, "grass00", false);
		setUp(3, "grass00", false);
		setUp(4, "grass00", false);
		setUp(5, "grass00", false);
		setUp(6, "grass00", false);
		setUp(7, "grass00", false);
		setUp(8, "grass00", false);
		setUp(9, "grass00", false);
		setUp(10, "grass00", false);
		
		// PLACEHOLDER
		setUp(11, "grass01", false);
		setUp(12, "water00", true);
		setUp(13, "water01", true);
		setUp(14, "water02", true);
		setUp(15, "water03", true);
		setUp(16, "water04", true);
		setUp(17, "water05", true);
		setUp(18, "water06", true);
		setUp(19, "water07", true);
		setUp(20, "water08", true);
		setUp(21, "water09", true);
		setUp(22, "water10", true);
		setUp(23, "water11", true);
		setUp(24, "water12", true);
		setUp(25, "water13", true);
		setUp(26, "road00", false);
		setUp(27, "road01", false);
		setUp(28, "road02", false);
		setUp(29, "road03", false);
		setUp(30, "road04", false);
		setUp(31, "road05", false);
		setUp(32, "road06", false);
		setUp(33, "road07", false);
		setUp(34, "road08", false);
		setUp(35, "road09", false);
		setUp(36, "road10", false);
		setUp(37, "road11", false);
		setUp(38, "road12", false);
		setUp(39, "earth", false);
		setUp(40, "wall", true);
		setUp(41, "tree", true);
	}
	
	public void setUp(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public void draw(Graphics g2) {
		int worldCol = 0;
		int worldRow = 0;
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screentX = worldX - gp.player.worldX + gp.player.screentX;
			int screentY = worldY - gp.player.worldY + gp.player.screentY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screentX &&
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screentX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screentY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screentY) {
			}
			
			g2.drawImage(tile[tileNum].image, screentX, screentY, null);
			
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}


















