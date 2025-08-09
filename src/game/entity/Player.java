package game.entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
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
		
		setDefaultValues();
		getPLayerImage();
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
	    up1 = setUp("/player/a1");
	    up2 = setUp("/player/a2");
	    down1 = setUp("/player/a3");
	    down2 = setUp("/player/a4");
	    left1 = setUp("/player/a5");
	    left2 = setUp("/player/a6");
	    right1 = setUp("/player/a7");
	    right2 = setUp("/player/a7");
	}
	
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true 
				|| keyH.leftPressed == true || keyH.rightPressed == true) {
			
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
			
			gp.keyH.enterPresed = false;
			
			// IF COLLSION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
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
	
	public void pickUpObject(int i) {
		if(i != 999) {
		}
	}
	
	public void interactNPC(int i) {
		if(i != 999) {
			
			if(gp.keyH.enterPresed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}

		}

	}
	
	public void contactMonter(int i) {
		if(i != 999) {
			
			if(invincible == false) {
				life -= 1;
				invincible = true;
			}

		}
	}
	
	public void draw(Graphics g) {
		
		BufferedImage image = null;
		Graphics2D g2 = (Graphics2D)g;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}			
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		int x = screentX;
		int y = screentY;
		
		if (screentX > worldX) {
			x = worldX;
		}
		if (screentY > worldY) {
			y = worldY;
		}
		int rightOffset = gp.screenWidth - screentX;
		if (rightOffset > gp.worldWidth - worldX) {
			x = gp.screenWidth - (gp.worldWidth -  worldX);
		}
		
		int bottomOffset = gp.screenHeight - screentY;
		if (bottomOffset > gp.worldHeight - worldY) {
			x = gp.screenHeight - (gp.worldHeight -  worldY);
		}
		
		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		g2.drawImage(image, x, y, null);
		
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





















