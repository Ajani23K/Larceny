package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import map.SuperMap;
import tile.TileManager;

public class Player extends Entity{
	
	
	KeyHandler keyH;
	TileManager tileM;
	SuperMap map[];
	public final int screenX;
	public final int screenY;
	public boolean inStore = false;
	
	public double cooldownTime = 1000000000; //1 second cooldown
	public double startTime = 0;
	
	public Player(GamePanel gp, KeyHandler keyH, TileManager tileM, SuperMap map[]) {
		super(gp);
		
		
		this.keyH = keyH;
		this.tileM = tileM;
		this.map = map;
		screenX = gp.ScreenWidth/2 - (gp.tileSize/2);
		screenY = gp.ScreenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(8,16,28,28); //collision area x,y,width, height
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y; //record default values because they will change later
	
	
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 2;
		worldY = gp.tileSize * 8;
		speed = 4;
		direction = "down";
		
		//player status
		//1 life = half heart, 2 life = full heart
		maxLife = 6;
		life = maxLife;
		
	}
	public void getPlayerImage(){
		
	
			//down
			down1 = setup("/player/LarcyWalkDown1");
			down2 = setup("/player/LarcyWalkDown2");
			down3 = setup("/player/LarcyWalkDown3");
			down4 = setup("/player/LarcyWalkDown4");
			down5 = setup("/player/LarcyWalkDown5");
			//left
			left1 = setup("/player/LarcyWalkLeft1");
			left2 = setup("/player/LarcyWalkLeft2");
			left3 = setup("/player/LarcyWalkLeft3");
			left4 = setup("/player/LarcyWalkLeft4");
			left5 = setup("/player/LarcyWalkLeft5");
			//right
			right1 = setup("/player/LarcyWalkRight1");
			right2 = setup("/player/LarcyWalkRight2");
			right3 = setup("/player/LarcyWalkRight3");
			right4 = setup("/player/LarcyWalkRight4");
			right5 = setup("/player/LarcyWalkRight5");
			//up
			up1 = setup("/player/LarcyWalkUp1");
			up2 = setup("/player/LarcyWalkUp2");
			up3 = setup("/player/LarcyWalkUp3");
			up4 = setup("/player/LarcyWalkUp4");
			up5 = setup("/player/LarcyWalkUp5");
			

		
	}
	public void update() {
		//check if up is pressed to move player sprite
		playerMovement();
	
		//collisionOn = false;
		//gp.colChecker.checkTile(this);
	}
	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.white);
		
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		//based on input direction we pick different images
		switch(direction) {
		case "up":
			if(spriteNum ==1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			if(spriteNum == 3) {
				image = up3;
			}
			if(spriteNum == 4) {
				image = up4;
			}
			if(spriteNum == 5) {
				image = up5;
			}
			break;
		case "down":
			if(spriteNum ==1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			if(spriteNum == 3) {
				image = down3;
			}
			if(spriteNum == 4) {
				image = down4;
			}
			if(spriteNum == 5) {
				image = down5;
			}
			break;
		case "left":
			if(spriteNum ==1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			if(spriteNum == 3) {
				image = left3;
			}
			if(spriteNum == 4) {
				image = left4;
			}
			if(spriteNum == 5) {
				image = left5;
			}
			break;
		case "right":
			if(spriteNum ==1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			if(spriteNum == 3) {
				image = right3;
			}
			if(spriteNum == 4) {
				image = right4;
			}
			if(spriteNum == 5) {
				image = right5;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, null);
		
	}
	public void playerMovement() {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true ||  keyH.leftPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
			
				
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			
				
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			
				
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			
			}
		collisionOn = false;
		gp.cChecker.checkTile(this); //pass player class as an entity to check for collision
		
		//check Object collision
		int objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		
		//check NPC collision
		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		
		//if collision is false, player can move 
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
		spriteCounter++;
		//player image changes every 3 frames
		if(spriteCounter > 3) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}else if(spriteNum == 2) {
				spriteNum = 3;
			}else if(spriteNum == 3) {
				spriteNum = 4;
			}else if(spriteNum == 4) {
				spriteNum = 5;
			}else if(spriteNum == 5) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		}
		
	}
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Dollar":
			
				break;
			case "Door":
			
				if(inStore == false) {
					if(System.nanoTime() - startTime >= cooldownTime) {
						tileM.changeMap(map[1]);
						inStore = true;
						startTime = System.nanoTime();
					}
				}
				else {	
					if(System.nanoTime() - startTime >= cooldownTime) {
					tileM.changeMap(map[0]);
					inStore = false;
					startTime = System.nanoTime();
					}
				}
				break;
			case "Trashcan":
				break;
			case "Soda":
				speed+=2;
				gp.ui.showMessage("You Feel Hastened!");
				gp.obj[i] = null;
				break;
			}
		}	
	}
	public void interactNPC(int i) {
		
		if(i != 999) {
			if(keyH.ePressed == true) {
			gp.gameState = gp.dialogueState;
			gp.npc[i].speak();
		}
			keyH.ePressed = false;
		}
		
	}
}