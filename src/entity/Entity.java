package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
	public int worldX,worldY;
	public int speed;
	
	public BufferedImage 	up1, up2, up3, up4, up5, 
							down1, down2, down3, down4, down5, 
							left1, left2, left3, left4, left5, 
							right1, right2, right3, right4, right5;
	public String direction = "down";
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public int actionLockCounter = 0;
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	
	String dialogues[] = new String[20];
	
	int dialogueIndex = 0;
	
	//Character Status
	public int maxLife;
	public int life;
	
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void setAction() {
		
	}
	public void speak() {
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		if(dialogues[dialogueIndex+1] != null) {
		dialogueIndex++;
		}else {
			dialogueIndex = 0;
		}
		
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
		
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		
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
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		//check if the tile being drawn is in players vision if it isnt in the players vision then it wont be drawn
		if(worldX + gp.tileSize*2 > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize*2 < gp.player.worldX + gp.player.screenX && 
		   worldY + gp.tileSize*2 > gp.player.worldY - gp.player.screenY && 
		   worldY - gp.tileSize*2 < gp.player.worldY + gp.player.screenY) {
			//paint tile in that position 
			switch(direction) {
			case "up":
				if(spriteNum ==1) {
					image = up1;
				}
				
				break;
			case "down":
				if(spriteNum ==1) {
					image = down1;
				}
				
				break;
			case "left":
				if(spriteNum ==1) {
					image = left1;
				}
			
				break;
			case "right":
				if(spriteNum ==1) {
					image = right1;
				}
		
				break;
			}
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	public BufferedImage setup(String imagePath) {
		//scaling up the images so it doesnt have to be done in draw method
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResource(imagePath+".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
