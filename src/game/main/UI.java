package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
//	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messagerCouter = 0;
	public boolean  gameFinished = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI (GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
//		OBJ_Key key = new OBJ_Key(gp);
//		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw (Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		if(gp.gameState == gp.playState) {
			//do play state stuff later
		}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
//		if (gameFinished == true) {
//			String text;
//			int textLength;
//			int x;
//			int y;
//			
//			g2.setFont(arial_40);
//			g2.setColor(Color.white);
//			
//			text = "you found the treasure!";
//			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//			x = gp.screenWidth/2 - textLength/2;
//			y = gp.screenHeight/2 - (gp.tileSize * 3);
//			g2.drawString(text, x, y);
//
//			text = "you time is:" + dFormat.format(playTime) + "!";
//			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//			x = gp.screenWidth/2 - textLength/2;
//			y = gp.screenHeight/2 + (gp.tileSize * 4);
//			g2.drawString(text, x, y);
//			
//			g2.setFont(arial_80B);
//			g2.setColor(Color.yellow);
//			text = "congratulations!";
//			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//			x = gp.screenWidth/2 - textLength/2;
//			y = gp.screenHeight/2 + (gp.tileSize * 3);
//			g2.drawString(text, x, y);
//			
//			gp.thread = null;
//		}
//		else {
//			g2.setFont(arial_40);
//			g2.setColor(Color.white);
//			//HIỂN THỊ TRÊN MÀN HÌNH
//			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
//			//HIỂN THỊ TRÊN MÀN HÌNH
//			g2.drawString("x " + gp.player.hasKey, 74, 65);
//			
//			//tính thời giam tìm kho báu
//			playTime += (double)1/60;
//			g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);
//			
//			//message
//			if (messageOn == true) {
//				g2.setFont(g2.getFont().deriveFont(30F));
//				//HIỂN THỊ TRÊN MÀN HÌNH
//				g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
//				
//				//tắt
//				messagerCouter++;
//				if(messagerCouter > 120) {
//					messagerCouter = 0;
//					messageOn = false;
//				}
//			}
//		}
	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public int getXForCenteredText (String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}


















