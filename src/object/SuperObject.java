package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {

	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		//check if the tile being drawn is in players vision if it isnt in the players vision then it wont be drawn
		if(worldX + gp.tileSize*2 > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize*2 < gp.player.worldX + gp.player.screenX && 
		   worldY + gp.tileSize*2 > gp.player.worldY - gp.player.screenY && 
		   worldY - gp.tileSize*2 < gp.player.worldY + gp.player.screenY) {
			//paint tile in that position 
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
		
	}
}
