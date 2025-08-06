package game.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.main.GamePanel;

public class OBJ_Chest extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_Chest(GamePanel gp) {
		name = "Chest";
		this.gp = gp;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/chest.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.getStackTrace();
		}
	}
}
