package main;

import java.awt.Rectangle;

public class EventHandler {

	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.width = 2;
		eventRect.height = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
	}
	
	public void checkEvent() {
		
		if(hit(2,15,"right")== true) {
			//event happens
			damagePit(gp.dialogueState);
		}
		if(hit(7,9, "any")== true) {
			
			healPool(gp.dialogueState);
		}
		
	}
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		boolean hit = false;
		
		//getting players current solid area
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		//get event rectangle position
		eventRect.x = eventCol*gp.tileSize + eventRect.x;
		eventRect.y = eventRow*gp.tileSize + eventRect.y;
		
		if(gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
			}
		}
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		return hit;
	}
	public void damagePit(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You fall in pit";
		gp.player.life -= 1;
	}
	public void healPool(int gameState) {
		if(gp.keyH.ePressed == true) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You rummage through some trash and find a small bite to eat";
		gp.player.life = gp.player.maxLife;
		}
		
	}
}
