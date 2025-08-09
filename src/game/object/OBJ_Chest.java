package game.object;

import game.entity.Entity;
import game.main.GamePanel;

public class OBJ_Chest extends Entity {
	
	GamePanel gp;
	
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		name = "Chest";
		down1 = setUp("/objects/chest");
	}
}
