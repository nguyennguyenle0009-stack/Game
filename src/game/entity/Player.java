package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import game.main.GamePanel;
import game.main.KeyHandler;
import game.main.UtilityTool;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screentX;
	public final int screentY;
	
//	public int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
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
	}
	
	public void getPLayerImage() {
	    up1 = setUp("a1");
	    up2 = setUp("a2");
	    down1 = setUp("a3");
	    down2 = setUp("a4");
	    left1 = setUp("a5");
	    left2 = setUp("a6");
	    right1 = setUp("a7");
	    right2 = setUp("a7");
	}
	
	public BufferedImage setUp(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/resources/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return image;
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
			//gp.cChecker.checkTile(this);
			
			//check object collsion
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
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

	}
	
	public void pickUpObject(int i) {
		if(i != 999) {
		}
	}
	
	public void draw(Graphics g2) {
		
		BufferedImage image = null;
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
		
		g2.drawImage(image, x, y, null);
		
		//g2.fillRect(screentX + solidArea.x, screentY + solidArea.y, solidArea.width, solidArea.height);
		
		//hiển thị đường biên giới nhân vật trong game
		
		g2.setColor(Color.red);
		g2.drawRect(screentX + solidArea.x, screentY + solidArea.y, solidArea.width, solidArea.height);
	}
	
}
