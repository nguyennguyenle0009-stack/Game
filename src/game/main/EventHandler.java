package game.main;

public class EventHandler {
	
	GamePanel gp;
	EventRect eventRect[][];
	
	int previousEventX, previousEventY;
	boolean canTounchEvent = true;
	
	public EventHandler (GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
		
		int col = 0;
		int row = 0;
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[col][row] = new EventRect();
			eventRect[col][row].x = 23;
			eventRect[col][row].y = 23;
			eventRect[col][row].width = 2;
			eventRect[col][row].height = 2;
			eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
			
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	
	public void checkEvent() {
		
		
		//check if the player character is more than 1 tile away from the last event
		int xDiastance = Math.abs(gp.player.worldX - previousEventX);
		int yDiastance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDiastance, yDiastance);
		if (distance > gp.tileSize) {
			canTounchEvent = true;
		}
		
		if (canTounchEvent == true) {
			if (hit(27, 16, "right") == true) { damagePit( 27, 16, gp.dialogueState); }
			if (hit(23, 19, "any") == true) { damagePit( 27, 16, gp.dialogueState); }
			//if (hit(27, 16, "right") == true) { teleport(gp.dialogueState); }
			if (hit(23, 12, "up") == true) { healingPool( 23, 12, gp.dialogueState); };
		}
	}
	
	public boolean hit(int col, int row, String reDirection) {
		
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
		
		if (gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
			if (gp.player.direction.contentEquals(reDirection) || reDirection.contentEquals("any")) {
				hit = true;
				
				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
		
		return hit;
	}
	
	public void damagePit(int col, int row, int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You fail into a pit!";
		gp.player.life -= 1;
		//eventRect[col][row].eventDone = true;
		canTounchEvent = false;
	}
	
	public void healingPool(int col, int row, int gameState) {
		if (gp.keyH.enterPresed == true) {
			gp.gameState = gameState;
			gp.ui.currentDialogue = "You drink the water.\nYour lift has been recovered";
			gp.player.life = gp.player.maxlife;
		}
	}
	
	public void teleport(int gameState){
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Teleport";
		gp.player.worldX = gp.tileSize * 37;
		gp.player.worldY = gp.tileSize * 10;
	}
}




















