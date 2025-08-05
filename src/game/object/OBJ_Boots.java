package game.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject{
	public OBJ_Boots() {
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/boots.png"));
		} catch(IOException e) {
			e.getStackTrace();
		}
		collision = true;
	}
}