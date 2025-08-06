package game.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.main.GamePanel;

public class OBJ_Door extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_Door(GamePanel gp) {
		name = "Door";
		this.gp = gp;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/door.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.getStackTrace();
		}
		this.collision = true;
	}
	
}
