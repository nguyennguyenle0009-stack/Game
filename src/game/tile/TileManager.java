package game.tile;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import game.main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
		getTileManager();
		loadMap("/resources/maps/world01.txt");
	}
	
	public void getTileManager() {
		
		try {
			tile[0] = new  Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grass00.png"));
			
			tile[1] = new  Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new  Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/water00.png"));
			tile[2].collision = true;
			
			tile[3] = new  Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/earth.png"));
			
			tile[4] = new  Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new  Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/sand.png"));
			
		} catch(IOException e) {
			e.getStackTrace();
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
			
			g2.drawImage(tile[tileNum].image, screentX, screentY, gp.tileSize, gp.tileSize, null);
			
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}


















