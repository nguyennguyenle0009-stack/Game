package game.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import game.main.GamePanel;
import game.main.UtilityTool;

public class Entity {
	GamePanel gp;
	//set player default position
	public int worldX, worldY;
	public int speed;
	//thêm ảnh
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public  String direction = "down";
	//hiệu ứng chuyển động
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public boolean collisionOn = false;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public int actionLockCounter = 0;
	public boolean invincible = false;
	public int invincibleCounter = 0;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	//chracter status
	public int maxlife;
	public int life;
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	public int type; // 0 = player, 1 = npc, 2 = monster
	
	public Entity (GamePanel gp) {
		this.gp = gp;
	}
	public void setAcction() {
		
	}
	public void speak() {
		if (dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	public void update() {
		
		setAcction();
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == 2 && contactPlayer == true) {
			if(gp.player.invincible == false) {
				//we can give damage
				gp.player.life -= 1;
				gp.player.invincible = true;
			}
		}
		
		
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
	public void draw (Graphics g2) {
		
		BufferedImage image = null;
		
		double screentX = worldX - gp.player.worldX + gp.player.screentX;
		double screentY = worldY - gp.player.worldY + gp.player.screentY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screentX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screentX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screentY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screentY) {
			
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
			
			g2.drawImage(image, (int)screentX, (int)screentY, gp.tileSize, gp.tileSize, null);
		}
	}
	public BufferedImage setUp(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/resources" + imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return image;
	}
}
