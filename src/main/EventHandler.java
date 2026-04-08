package main;

import java.awt.Rectangle;

public class EventHandler {

	GamePanel gp;
	EventRect eventRect[][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.MaxWorldCol][gp.MaxWorldRow];
		
		int col = 0;
		int row = 0;
		while(col < gp.MaxWorldCol && row < gp.MaxWorldRow) {
			eventRect[col][row] = new EventRect();
			eventRect[col][row].x = 17;
			eventRect[col][row].y  = 17;
			eventRect[col][row].width  = 8;
			eventRect[col][row].height  = 8;
			eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
			
			col++;
			if(col == gp.MaxWorldCol) {
				col = 0;
				row++;
			}
		}
		
	}
	
	public void checkEvent() {
		//check if player character is more than 1 tile away from last event
		
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if(canTouchEvent == true) {
		if(hit(2,15,"any")== true) {
			//event happens
			damagePit(2,15,gp.dialogueState);
		}
		if(hit(7,9, "any")== true) {
			
			healPool(7,9,gp.dialogueState);
		}
		}
		
	}
	public boolean hit(int col, int row, String reqDirection) {
		boolean hit = false;
		
		//getting players current solid area
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		//get event rectangle position
		eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;
		
		if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone != true) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
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
		gp.ui.currentDialogue = "You fall in pit";
		gp.player.life -= 1;
		//eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
	public void healPool(int col, int row,int gameState) {
		if(gp.keyH.ePressed == true) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You rummage through some trash and find a small bite to eat";
		gp.player.life = gp.player.maxLife;
		}
		
	}
}
