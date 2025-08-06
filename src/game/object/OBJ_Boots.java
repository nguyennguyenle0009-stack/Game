package game.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.main.GamePanel;

public class OBJ_Boots extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Boots(GamePanel gp) {
		name = "Boots";
		this.gp = gp;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/boots.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.getStackTrace();
		}
		collision = true;
	}
}