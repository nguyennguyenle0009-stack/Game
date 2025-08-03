package game.entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	//set player default position
	public double worldX, worldY;
	public double speed;
	
	//thêm ảnh
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public  String direction;
	
	//hiệu ứng chuyển động
	public int spriteCounter = 0;
	public int spriteNum = 1;
}
