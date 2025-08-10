package game.object;

import game.entity.Entity;
import game.main.GamePanel;

public class OBJ_Boots extends Entity{
	
	GamePanel gp;
	
	public OBJ_Boots(GamePanel gp) {
		super(gp);
		name = "Boots";
		down1 = setUp("/objects/boots", gp.tileSize, gp.tileSize);
		collision = true;
	}
}