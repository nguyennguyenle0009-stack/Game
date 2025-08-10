package game.object;

import game.entity.Entity;
import game.main.GamePanel;

public class OBJ_Key extends Entity{
	
	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		super(gp);
		name = "Key";
		down1 = setUp("/objects/key", gp.tileSize, gp.tileSize);
		
	}
}
