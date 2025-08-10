package game.entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.main.GamePanel;
import game.main.KeyHandler;

public class Player extends Entity{
	
	KeyHandler keyH;
	
	public final int screentX;
	public final int screentY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.keyH = keyH;
		
		screentX = gp.screenWidth/2 - (gp.tileSize/2);
		screentY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		attackArea.width = 36;
		attackArea.height = 36;
		
		setDefaultValues();
		getPLayerImage();
		getPlayerAttackImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
		
		//PLAYER STATUS
		maxlife = 6;
		life = maxlife;
	}
	
	public void getPLayerImage() {
	    up1 = setUp("/player/a1", gp.tileSize, gp.tileSize);
	    up2 = setUp("/player/a2", gp.tileSize, gp.tileSize);
	    down1 = setUp("/player/a3", gp.tileSize, gp.tileSize);
	    down2 = setUp("/player/a4", gp.tileSize, gp.tileSize);
	    left1 = setUp("/player/a5", gp.tileSize, gp.tileSize);
	    left2 = setUp("/player/a6", gp.tileSize, gp.tileSize);
	    right1 = setUp("/player/a7", gp.tileSize, gp.tileSize);
	    right2 = setUp("/player/a7", gp.tileSize, gp.tileSize);
	}
	
	public void getPlayerAttackImage() {
		attackUp1 = setUp("/player/boy_attack_up_1", gp.tileSize, gp.tileSize);
		attackUp2 = setUp("/player/boy_attack_up_2", gp.tileSize, gp.tileSize);
		attackDown1 = setUp("/player/boy_attack_down_1", gp.tileSize, gp.tileSize);
		attackDown2 = setUp("/player/boy_attack_down_2", gp.tileSize, gp.tileSize);
		attackLeft1 = setUp("/player/boy_attack_left_1", gp.tileSize, gp.tileSize);
		attackLeft2 = setUp("/player/boy_attack_left_2", gp.tileSize, gp.tileSize);
		attackRight1 = setUp("/player/boy_attack_right_1", gp.tileSize, gp.tileSize);
		attackRight2 = setUp("/player/boy_attack_right_2", gp.tileSize, gp.tileSize);
	}
	
	public void update() {
		
		if(attacking == true) {
			attacking();
		}
		
		if(keyH.upPressed == true || keyH.downPressed == true 
				|| keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPresed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			// CHECK TILE COLLSION
			collisionOn = false;
			//Va chạm
			gp.cChecker.checkTile(this);
			
			//check object collsion
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonter(monsterIndex);
			
			//check event
			gp.eHandler.checkEvent();
			

			
			// IF COLLSION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false && keyH.enterPresed == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			gp.keyH.enterPresed = false;
			
			// => kiểm tra va chạm nên người chơi chỉ có thể di chuyển vào ô không rắn
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		//This needs to be outside of key statement!
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	
	public void attacking () {
		spriteCounter++;
		
		if(spriteCounter <= 5) {
			spriteNum = 1;
		}
		
		if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			
			//Save the current worldX, worldY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			//Adjust player's worldX/Y for the attackArea
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			
			//attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			//Check monster collision with the update worldX/Y and solidArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
			
		}
		
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void pickUpObject(int i) {
		if(i != 999) {
		}
	}
	
	public void interactNPC(int i) {
		if(gp.keyH.enterPresed == true) {
			if(i != 999) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
			else {
				gp.playSE(7);
				attacking = true;
			}
		}
	}
	
	public void contactMonter(int i) {
		if(i != 999) {
			
			if(invincible == false) {
				gp.playSE(6);
				life -= 1;
				invincible = true;
			}

		}

	}
	
	public void damageMonster(int i) {
		if(i != 999) {
			if (gp.monster[i].invincible == false) {
				
				gp.playSE(5);
				gp.monster[i].life -= 1;
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();
				
				if (gp.monster[i].life <= 0) {
					gp.monster[i].dying = true;
				}
			}
		}
	}
	
	public void draw(Graphics g) {
		
		BufferedImage image = null;
		Graphics2D g2 = (Graphics2D)g;
		int tempScreenX = screentX;
		int tempScreenY = screentY;
		
		switch(direction) {
		case "up": 
			if (attacking == false) {
				if (spriteNum == 1) { image = up1; }
				if (spriteNum == 2) { image = up2; }
			}
			if (attacking == true) {
				tempScreenY = screentY - gp.tileSize;
				if (spriteNum == 1) { image = attackUp1; }
				if (spriteNum == 2) { image = attackUp2; }
			}
			break;
		case "down":
			if (attacking == false) {
				if (spriteNum == 1) { image = down1; }			
				if (spriteNum == 2) { image = down2; }
			}
			if (attacking == true) {
				tempScreenY = screentY + gp.tileSize;
				if (spriteNum == 1) { image = attackDown1; }
				if (spriteNum == 2) { image = attackDown2; }
			}
			break;
		case "left":
			if (attacking == false) {
				if (spriteNum == 1) { image = left1; }
				if (spriteNum == 2) { image = left2; }
			}
			if (attacking == true) {
				tempScreenX = screentX - gp.tileSize;
				if (spriteNum == 1) { image = attackLeft1; }
				if (spriteNum == 2) { image = attackLeft2; }
			}
			break;
		case "right":
			if (attacking == false) {
				if (spriteNum == 1) { image = right1; }
				if (spriteNum == 2) { image = right2; }
			}
			if (attacking == true) {
				tempScreenX = screentX + gp.tileSize;
				if (spriteNum == 1) { image = attackRight1; }
				if (spriteNum == 2) { image = attackRight2; }
			}
			break;
		}
		
		if (screentX > worldX) {
			tempScreenX = worldX;
		}
		if (screentY > worldY) {
			tempScreenY = worldY;
		}
		int rightOffset = gp.screenWidth - screentX;
		if (rightOffset > gp.worldWidth - worldX) {
			tempScreenX = gp.screenWidth - (gp.worldWidth -  worldX);
		}
		
		int bottomOffset = gp.screenHeight - screentY;
		if (bottomOffset > gp.worldHeight - worldY) {
			tempScreenY = gp.screenHeight - (gp.worldHeight -  worldY);
		}
		
		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		
		//Reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		
		
		//g2.fillRect(screentX + solidArea.x, screentY + solidArea.y, solidArea.width, solidArea.height);
		
		//hiển thị đường biên giới nhân vật trong game
		
		g2.setColor(Color.red);
		g2.drawRect(screentX + solidArea.x, screentY + solidArea.y, solidArea.width, solidArea.height);
		
		//DEBUG
//		g2.setFont(new Font("Arial", Font.PLAIN, 26));
//		g2.setColor(Color.white);
//		g2.drawString("invincible: " + invincibleCounter, 10, 400);
		
	}
	
}





















