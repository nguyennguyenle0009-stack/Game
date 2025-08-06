package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.main.GamePanel;
import game.main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screentX;
	public final int screentY;
	
	public int hasKey = 0;
	
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
	    try {
	    	up1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/a1.png"));
	    	up2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/a2.png"));
	    	down1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/a3.png"));
	    	down2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/a4.png"));
	    	left1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/a5.png"));
	    	left2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/a6.png"));
	    	right1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/a7.png"));
	    	right2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/a8.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
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
			gp.cChecker.checkTile(this);
			
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
			//gp.obj[i] = null;
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key":
				gp.playSE(1);
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You got a key!"); //thay thế System.out.println("Key: " + hasKey);
				break;
			case "Door":
				if(hasKey > 0) {
					gp.playSE(3);
					hasKey--;
					gp.obj[i] = null;
					gp.ui.showMessage("You opened the door!");
				} else {
					gp.ui.showMessage("You need a key!");
				}
				break;
			case "Boots":
				gp.playSE(2);
				speed +=2;
				gp.obj[i] = null;
				//System.out.println("speed: " + speed);
				gp.ui.showMessage("speed up + 2!");
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playSE(4);
				break;
			}
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
		g2.drawImage(image, screentX, screentY, gp.tileSize, gp.tileSize, null);
		//hiển thị đường biên giới nhân vật trong game
		g2.setColor(Color.red);
		g2.drawRect(screentX + solidArea.x, screentY + solidArea.y, solidArea.width, solidArea.height);
	}
	
}
