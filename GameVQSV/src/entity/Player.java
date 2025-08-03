package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.main.GamePanel;
import game.main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPLayerImage();
	}
	
	public void setDefaultValues() {
		
		x = 200;
		y = 200;
		speed = 4;
		direction = "down";
	}
	
	public void getPLayerImage() {
	    try {
//	        up1 = ImageIO.read(getClass().getResource("/82b3b50a-902f-46b6-8fa5-6fff389a079d.png"));
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
//		lần 1
//		if(keyH.upPressed == true) {
//			direction = "up";
//			y -= speed;
//		}
//		else if(keyH.downPressed == true) {
//			direction = "down";
//			y += speed;
//		}
//		else if(keyH.leftPressed == true) {
//			direction = "left";
//			x -= speed;
//		}
//		else if(keyH.rightPressed == true) {
//			direction = "right";
//			x += speed;
//		}
		if(keyH.upPressed == true || keyH.downPressed == true 
				|| keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
				y -= speed;
			}
			else if(keyH.downPressed == true) {
				direction = "down";
				y += speed;
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
				x -= speed;
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
				x += speed;
			}
			
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
	
	public void draw(Graphics g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.titleSize, gp.titleSize);
		
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
		g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);
	}
	
//lần 1
//	public void draw(Graphics g2) {
////		g2.setColor(Color.white);
////		g2.fillRect(x, y, gp.titleSize, gp.titleSize);
//		
//		BufferedImage image = null;
//		switch(direction) {
//		case "up":
//			image = up1;
//			break;
//		case "down":
//			image = down1;
//			break;
//		case "left":
//			image = left1;
//			break;
//		case "right":
//			image = right1;
//			break;
//		}
//		g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);
//	}
	
}
































