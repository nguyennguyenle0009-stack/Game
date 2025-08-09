package game.monster;

import java.util.Random;

import game.entity.Entity;
import game.main.GamePanel;

public class MON_GreenSlime extends Entity{

	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		
		type = 2;
		name = "Green Slime";
		speed = 1;
		maxlife = 4;
		life = maxlife;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		setAcction();
		
	}
	
	public void getImage() {
		
		up1 = setUp("/monster/greenslime_down_1");
		up2 = setUp("/monster/greenslime_down_2");
		down1 = setUp("/monster/greenslime_down_1");
		down2 = setUp("/monster/greenslime_down_2");
		left1 = setUp("/monster/greenslime_down_1");
		left2 = setUp("/monster/greenslime_down_2");
		right1 = setUp("/monster/greenslime_down_1");
		right2 = setUp("/monster/greenslime_down_2");
		
	}
	
	public void setAcction() {
		
		actionLockCounter ++;
		
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <=100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
		
	}

}

















