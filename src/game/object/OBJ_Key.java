package game.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.main.GamePanel;

public class OBJ_Key extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		name = "Key";
		this.gp = gp;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.getStackTrace();
		}
		collision = true;
	}
}
