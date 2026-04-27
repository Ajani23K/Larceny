package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
	public int worldX,worldY;

	
	public BufferedImage 	up1, up2, up3, up4, up5, 
							down1, down2, down3, down4, down5, 
							left1, left2, left3, left4, left5, 
							right1, right2, right3, right4, right5;
	
	public BufferedImage 	attackup1, attackup2, attackup3, attackup4, attackup5, 
	attackdown1, attackdown2, attackdown3, attackdown4, attackdown5, 
	attackleft1, attackleft2, attackleft3, attackleft4, attackleft5, 
	attackright1, attackright2, attackright3, attackright4, attackright5;
	
	public String direction = "down";
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	
	public boolean monster = false;
	public int actionLockCounter = 0;
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	
	String dialogues[] = new String[20];
	
	public int type;
	int dialogueIndex = 0;
	public boolean alive = true;
	public boolean dying = false;
	int dyingCounter;
	boolean hpBarOn = false;
	int hpBarCounter;
	public int invincibleCounter;
	
	//Character Status
	public int maxLife;
	public int life;
	public int speed;
	public boolean attacking = false;
	public boolean invincible = false;
	
	
	
	
	
	
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void setAction() {
		
	}
	public void damageReaction() {
		
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
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == 2 && contactPlayer == true) {
			if(gp.player.invincible == false) {
				gp.player.life -= 1;
				gp.player.invincible = true;
			}
		}
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
		if(invincible) {
			invincibleCounter++;
			if(invincibleCounter> 30) {
				invincible = false;
				invincibleCounter = 0;
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
			
			//Display monster health
			if(monster == true && hpBarOn == true) {
				double oneScale = (double)gp.tileSize/maxLife;
				double hpBarValue = oneScale*life;
				
				g2.setColor(new Color(0,0,0));
				g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
				
				g2.setColor(new Color(250, 0, 30));
				g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
				
				hpBarCounter++;
				if(hpBarCounter > 600) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
				
			}
			if(invincible) {
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2, 0.4f);
			}
			
			if(gp.keyH.showHitbox) {
				System.out.println("showhitbox true");
				if(monster == true) {
					g2.setColor(Color.white);
				g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
				}
			}
			if(dying == true) {
				dyingAnimation(g2);
			}
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			//reset opacity
			changeAlpha(g2, 1f);
		}
	}
	public void dyingAnimation(Graphics2D g2) {
		//show blinking effect when enemy dies
		dyingCounter++;
		
		int i = 5;
		
		if(dyingCounter <= i) {
	
			changeAlpha(g2, 0f);
		}
		if(dyingCounter > i && dyingCounter <= i*2) {
	
			changeAlpha(g2, 1f);
		}
		if(dyingCounter > i*2 && dyingCounter <= i*3) {
	
			changeAlpha(g2, 0f);
		}
		if(dyingCounter > i*3 && dyingCounter <= i*4) {
		
			changeAlpha(g2, 1f);
		}
		if(dyingCounter > i*4 && dyingCounter <= i*5) {
		
			changeAlpha(g2, 0f);
		}
		if(dyingCounter > i*5 && dyingCounter <= i*6) {
			
			changeAlpha(g2, 1f);
		}
		if(dyingCounter > i*6 && dyingCounter <= i*7) {
			
			changeAlpha(g2, 0f);
		}
		if(dyingCounter > i*7 && dyingCounter <= i*8) {
		
			changeAlpha(g2, 1f);
		}
		if(dyingCounter > i*8) {
			dying = false;
			alive = false;
		}
		
	}
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
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
	public BufferedImage setup(String imagePath, int width, int height) {
		//scaling up the images so it doesnt have to be done in draw method
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResource(imagePath+".png"));
			image = uTool.scaleImage(image, width, height);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public String getPlayerDirection() {
		return gp.player.direction;
	}
	
}
